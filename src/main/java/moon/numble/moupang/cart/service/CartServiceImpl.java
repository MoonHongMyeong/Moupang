package moon.numble.moupang.cart.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.repository.CartRepository;
import moon.numble.moupang.cart.dto.CartResponseDto;
import moon.numble.moupang.cart.dto.CartSaveRequestDto;
import moon.numble.moupang.cart.dto.CartUpdateRequestDto;
import moon.numble.moupang.cart.exception.CartNotFoundException;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
import moon.numble.moupang.product.domain.repository.ClothesOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.exception.ProductNotFoundException;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ClothesOptionRepository clothesOptionRepository;

    public CartResponseDto createCartItem(CartSaveRequestDto dto, User user, Long productId) {

        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty()){
            throw new ProductNotFoundException(productId.toString());
        }

        Optional<ProductOption> option = productOptionRepository.findById(dto.getProductOptionId());

        if(option.isEmpty()){
            throw new EntityNotFoundException(dto.getProductOptionId().toString());
        }

        ClothesOption clothesOption = clothesOptionRepository.getById(dto.getClothesOptionId());

        Cart cartItem = cartRepository.save(dto.toEntity(user, product.get(), option.get(), clothesOption));

        return CartResponseDto.of(cartItem);

    }

    public CartResponseDto updateCartItem(CartUpdateRequestDto dto, Long cartId) {

        Cart cart = getCartById(cartId);

        cart.updateQuantity(dto.getQuantity());

        return CartResponseDto.of(cart);
    }

    public Cart getCartById(Long cartId) {

        Optional<Cart> cart = cartRepository.findById(cartId);

        if(cart.isEmpty()){
            throw new CartNotFoundException(cartId.toString());
        }

        return cart.get();
    }

    public void deleteCartItem(Long cartId) {

        Cart cart = getCartById(cartId);

        cartRepository.delete(cart);
    }

    @Transactional(readOnly = true)
    public List<CartResponseDto> getCartItems(Long userId) {
        
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            throw new UserNotFoundException(userId.toString());
        }

        return cartRepository.getCartItems(user.get()).stream()
                .map(cartItem -> CartResponseDto.of(cartItem))
                .collect(Collectors.toList());
    }
}
