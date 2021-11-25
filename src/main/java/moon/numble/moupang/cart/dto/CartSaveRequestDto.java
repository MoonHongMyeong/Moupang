package moon.numble.moupang.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
import moon.numble.moupang.user.domain.entity.User;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CartSaveRequestDto {

    @NotEmpty
    private String status;
    @NotNull
    private int quantity;
    @NotNull
    private Long productOptionId;
    @Nullable
    private Long clothesOptionId;

    @Builder
    public CartSaveRequestDto(String status, int quantity, Long productOptionId, Long clothesOptionId) {
        this.status = status;
        this.quantity = quantity;
        this.productOptionId=productOptionId;
        this.clothesOptionId=clothesOptionId;
    }

    public Cart toEntity(User user, Product product, ProductOption option, ClothesOption clothesOption){
        return Cart.builder()
                .product(product)
                .user(user)
                .status(CartStatus.CART)
                .quantity(this.quantity)
                .option(option)
                .clothes_option(clothesOption)
                .build();
    }

    public Cart toEntity(User user, Product product, CartStatus directPurchase, ProductOption option, ClothesOption clothesOption) {
        return Cart.builder()
                .product(product)
                .user(user)
                .status(directPurchase)
                .quantity(this.quantity)
                .option(option)
                .clothes_option(clothesOption)
                .build();
    }
}
