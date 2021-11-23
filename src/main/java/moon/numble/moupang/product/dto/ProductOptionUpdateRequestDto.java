package moon.numble.moupang.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductOptionUpdateRequestDto {
    @NotEmpty
    private String title;
    @NotNull
    private Double price;
}
