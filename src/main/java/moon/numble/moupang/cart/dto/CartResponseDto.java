package moon.numble.moupang.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.product.domain.entity.Product;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Product product;
    private CartStatus status;
    private int quantity;

    @Builder
    public CartResponseDto(Long id, Product product, CartStatus status, int quantity) {
        this.cartId = id;
        this.product = product;
        this.status = status;
        this.quantity = quantity;
    }

    public static CartResponseDto of(Cart cartItem){
        return CartResponseDto.builder()
                .id(cartItem.getId())
                .product(cartItem.getProduct())
                .status(cartItem.getStatus())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
