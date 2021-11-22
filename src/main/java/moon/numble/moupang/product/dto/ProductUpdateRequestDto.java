package moon.numble.moupang.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.product.domain.entity.Company;
import moon.numble.moupang.product.domain.entity.Goldbox;
import moon.numble.moupang.product.domain.entity.RocketShipping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    @NotNull
    private Category type;
    @NotBlank
    private String title;
    @NotNull
    private Company company;
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
    private Goldbox isGoldBox;
    @NotNull
    private RocketShipping isRocketShipping;
    @NotNull
    private String thumbnailUrl;
    @NotNull
    private String detailUrl;
}
