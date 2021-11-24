package moon.numble.moupang.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.user.domain.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CartSaveRequestDto {

    @NotEmpty
    private String status;
    @NotNull
    private int quantity;

    @Builder
    public CartSaveRequestDto(String status, int quantity) {
        this.status = status;
        this.quantity = quantity;
    }

    public Cart toEntity(User user, Product product){
        return Cart.builder()
                .product(product)
                .user(user)
                .status(CartStatus.valueOf(this.status))
                .quantity(this.quantity)
                .build();
    }

}
