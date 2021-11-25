package moon.numble.moupang.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private Long cartId;
    private Product product;
    private CartStatus status;
    private int quantity;
    private ProductOption option;
    private ClothesOption clothes_option;

    @Builder
    public CartResponseDto(Long id, Product product, CartStatus status, int quantity, ProductOption option, ClothesOption clothesOption) {
        this.cartId = id;
        this.product = product;
        this.status = status;
        this.quantity = quantity;
        this.option = option;
        this.clothes_option = clothesOption;
    }

    public static CartResponseDto of(Cart cartItem){
        return CartResponseDto.builder()
                .id(cartItem.getId())
                .product(cartItem.getProduct())
                .status(cartItem.getStatus())
                .quantity(cartItem.getQuantity())
                .option(cartItem.getOption())
                .clothesOption(cartItem.getClothes_option())
                .build();
    }
}
