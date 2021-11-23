package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ClothesOptionUpdateRequestDto {
    @NotNull
    private boolean isClothes;
    @NotEmpty
    private String size;
    @NotEmpty
    private String color;

    @Builder
    public ClothesOptionUpdateRequestDto(boolean isClothes, String size, String color) {
        this.isClothes = isClothes;
        this.size = size;
        this.color = color;
    }
}
