package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.product.domain.entity.ClothesOption;

@Getter
@NoArgsConstructor
public class ClothesOptionResponseDto {
    private boolean isClothes;
    private String size;
    private String color;

    @Builder
    public ClothesOptionResponseDto(boolean isClothes, String size, String color) {
        this.isClothes = isClothes;
        this.size = size;
        this.color = color;
    }

    public static ClothesOptionResponseDto of(ClothesOption option){
        return ClothesOptionResponseDto.builder()
                .isClothes(option.isClothes())
                .size(option.getSize())
                .color(option.getColor())
                .build();
    }
}
