package moon.numble.moupang.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @NotNull
    private Long type;
    @NotBlank
    private String title;
    @NotNull
    private String company;
    @NotNull
    private int stock;
    @NotNull
    private int salesVolume;
    @NotNull
    private double price;
    @NotNull
    private int discountRate;
    @NotNull
    private double discountPrice;
    @NotNull
    private String isGoldBox;
    @NotNull
    private String isRocketShipping;
    @NotNull
    private String thumbnailUrl;
    @NotNull
    private String detailUrl;
}
