package moon.numble.moupang.order.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.cart.domain.repository.CartRepository;
import moon.numble.moupang.cart.dto.CartSaveRequestDto;
import moon.numble.moupang.cart.service.CartService;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.OrderStep;
import moon.numble.moupang.order.domain.repository.OrderDetailRepository;
import moon.numble.moupang.order.domain.repository.OrderRepository;
import moon.numble.moupang.order.dto.OrderListResponseDto;
import moon.numble.moupang.order.dto.OrderResponseDto;
import moon.numble.moupang.order.dto.OrderListSaveRequestDto;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.exception.ProductNotFoundException;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    public OrderResponseDto directlyPurchase(CartSaveRequestDto dto, User user, Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty()){
            throw new ProductNotFoundException(productId.toString());
        }

        Cart newCart = cartRepository.save(dto.toEntity(user, product.get(), CartStatus.DIRECT_PURCHASE));

        ProductOrder order = createOrder(user);
        double shippingFee = calculateShippingFee(newCart);

        OrderDetail orderDetail = orderDetailRepository.save(OrderDetail.builder()
                .calculatePrice(newCart.getQuantity() * newCart.getProduct().getPrice())
                .order(order)
                .cart(newCart)
                .quantity(newCart.getQuantity())
                .shippingFee(shippingFee)
                .build());

        product.get().purchaseProduct(orderDetail.getQuantity());

        order.orderComplete(orderDetail.getCalculatePrice() + orderDetail.getShippingFee());

        List<OrderDetail> details = new ArrayList<>();
        details.add(orderDetail);
        order.inputDetail(details);

        return OrderResponseDto.of(order);
    }

    private ProductOrder createOrder(User user) {
        return orderRepository.save(ProductOrder.builder()
                .orderStep(OrderStep.CREATE)
                .user(user)
                .build());
    }

    private double calculateShippingFee(Cart cart) {

        double calculatePrice = cart.getProduct().getPrice() * cart.getQuantity();

        if(calculatePrice >= 19800){
            return 0;
        }

        return 3000;
    }
    @Transactional(rollbackFor = Exception.class)
    public OrderListResponseDto purchaseFromCart(List<Cart> carts, User user) {

        ProductOrder order = createOrder(user);

        List<OrderDetail> details = carts.stream()
                .map(cart -> orderDetailRepository.save(OrderDetail.builder()
                        .cart(cart)
                        .quantity(cart.getQuantity())
                        .order(order)
                        .shippingFee(calculateShippingFee(cart))
                        .calculatePrice(calculateCartItemPrice(cart))
                        .build()))
                .collect(Collectors.toList());

        double totalPrice = details.stream().mapToDouble(detail -> (detail.getCalculatePrice() + detail.getShippingFee())).sum();
        order.orderComplete(totalPrice);
        order.inputDetail(details);
        
        return OrderListResponseDto.of(order);

    }

    private double calculateCartItemPrice(Cart cart) {

        double price = 0;

        if(cart.getProduct().getDiscountPrice() != 0){
            price = (cart.getProduct().getPrice() - cart.getProduct().getDiscountPrice()) * cart.getQuantity();
        }

        if(cart.getProduct().getDiscountRate() != 0.0){
            price = cart.getProduct().getPrice() * cart.getQuantity() * (100.0-cart.getProduct().getDiscountRate()) / 100;
        }

        return price;
    }
}
