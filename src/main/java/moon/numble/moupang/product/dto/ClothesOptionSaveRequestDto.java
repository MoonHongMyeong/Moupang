package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ClothesOptionSaveRequestDto {
    @NotNull
    private boolean isClothes;
    @NotEmpty
    private String size;
    @NotEmpty
    private String color;

    @Builder
    public ClothesOptionSaveRequestDto(boolean isClothes, String size, String color) {
        this.isClothes = true;
        this.size = size;
        this.color = color;
    }

    public ClothesOption toEntity(Product product){
        return ClothesOption.builder()
                .isClothes(true)
                .product(product)
                .size(this.size)
                .color(this.color)
                .build();
    }
}
