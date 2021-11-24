package moon.numble.moupang.cart.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CartUpdateRequestDto {
    @NotNull
    private int quantity;
}
