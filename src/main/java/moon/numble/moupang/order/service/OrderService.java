package moon.numble.moupang.order.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.address.domain.repository.ShippingAddressRepository;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.cart.domain.repository.CartRepository;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.InvalidValueException;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.OrderStep;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.order.domain.repository.OrderDetailRepository;
import moon.numble.moupang.order.domain.repository.OrderRepository;
import moon.numble.moupang.order.dto.DirectOrderRequestDto;
import moon.numble.moupang.order.dto.OrderListResponseDto;
import moon.numble.moupang.order.dto.OrderResponseDto;
import moon.numble.moupang.order.exception.OrderNotFoundException;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Goldbox;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
import moon.numble.moupang.product.domain.repository.ClothesOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.exception.ProductNotFoundException;
import moon.numble.moupang.user.domain.entity.IsMembership;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ClothesOptionRepository clothesOptionRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    @Transactional(rollbackFor = Exception.class)
    public OrderResponseDto directlyPurchase(DirectOrderRequestDto dto, User user, Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId.toString()));

        ShippingAddress address = shippingAddressRepository.findById(dto.getShippingAddressId())
                .orElseThrow(() -> new InvalidValueException("배송지 없음"));

        ProductOption option = productOptionRepository.findById(dto.getProductOptionId())
                .orElseThrow(()-> new EntityNotFoundException(dto.getProductOptionId().toString()));

        ClothesOption clothesOption = clothesOptionRepository.findById(dto.getClothesOptionId())
                .orElseThrow(()-> new EntityNotFoundException(dto.getClothesOptionId().toString()));

        Cart cart = cartRepository.save(Cart.builder()
                        .user(user)
                        .quantity(dto.getQuantity())
                        .status(CartStatus.valueOf(dto.getStatus()))
                        .option(option)
                        .clothes_option(clothesOption)
                        .build());

        ProductOrder order = createOrder(user, address);

        double shippingFee = calculateShippingFee(cart, user);

        OrderDetail orderDetail = orderDetailRepository.save(OrderDetail.builder()
                .calculatePrice(calculateCartItemPrice(cart, user))
                .order(order)
                .cart(cart)
                .quantity(cart.getQuantity())
                .shippingFee(shippingFee)
                .build());

        product.purchaseProduct(orderDetail.getQuantity());

        order.orderComplete(orderDetail.getCalculatePrice(),orderDetail.getShippingFee());

        List<OrderDetail> details = new ArrayList<>();
        details.add(orderDetail);
        order.inputDetail(details);

        return OrderResponseDto.of(order);
    }

    private ProductOrder createOrder(User user, ShippingAddress address) {
        return orderRepository.save(ProductOrder.builder()
                        .orderStep(OrderStep.CREATE)
                        .shippingAddress(address)
                        .user(user)
                        .build());
    }

    private double calculateShippingFee(Cart cart, User user) {

        double calculatePrice = cart.getProduct().getPrice() * cart.getQuantity();

        if(user.getMembership().equals(IsMembership.MEMBERSHIP)){
            return 0;
        }

        if(calculatePrice >= 19800){
            return 0;
        }

        return 3000;
    }
    @Transactional(rollbackFor = Exception.class)
    public OrderListResponseDto purchaseFromCart(List<Cart> carts, User user, Long addressId) {

        ShippingAddress address = shippingAddressRepository.findById(addressId)
                .orElseThrow(()-> new EntityNotFoundException("배송지가 존재하지 않습니다."));

        ProductOrder order = createOrder(user, address);

        List<OrderDetail> details = carts.stream()
                .map(cart -> orderDetailRepository.save(OrderDetail.builder()
                        .cart(cart)
                        .quantity(cart.getQuantity())
                        .order(order)
                        .shippingFee(calculateShippingFee(cart, user))
                        .calculatePrice(calculateCartItemPrice(cart, user))
                        .build()))
                .collect(Collectors.toList());

        double totalPrice = details.stream()
                .mapToDouble(detail -> detail.getCalculatePrice()).sum();

        double totalFee = details.stream()
                .mapToDouble(detail -> detail.getShippingFee()).sum();

        order.orderComplete(totalPrice, totalFee);
        order.inputDetail(details);

        return OrderListResponseDto.of(order);

    }

    private double calculateCartItemPrice(Cart cart, User user) {

        double price = 0;

        if(cart.getProduct().getDiscountPrice() != 0){
            price = (cart.getOption().getPrice() - cart.getProduct().getDiscountPrice()) * cart.getQuantity();
        }

        if(cart.getProduct().getDiscountRate() != 0.0){
            price = cart.getOption().getPrice() * cart.getQuantity() * (100.0-cart.getProduct().getDiscountRate()) / 100;
        }

        if(user.getMembership().equals(IsMembership.MEMBERSHIP) && cart.getProduct().getIsGoldBox().equals(Goldbox.GOLD_BOX)){
            price = price * 90 / 100;
        }

        return price;
    }

    public void cancelOrder(Long userId, Long orderId) {

        ProductOrder order = orderRepository.findById(orderId)
                .orElseThrow(()-> new OrderNotFoundException(orderId.toString()));

        order.orderCancel();
    }
}
