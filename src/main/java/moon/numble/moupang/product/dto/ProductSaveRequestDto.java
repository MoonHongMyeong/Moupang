package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.product.domain.entity.Company;
import moon.numble.moupang.product.domain.entity.Goldbox;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.RocketShipping;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {

    @NotNull
    private Long type;
    @NotEmpty
    private String title;
    @NotNull
    private String company;
    @NotNull
    private int stock;
    @NotNull
    private int salesVolume;
    @NotNull
    private double price;
    @Nullable
    private int discountRate;
    @Nullable
    private double discountPrice;
    @NotNull
    private String isGoldBox;
    @NotNull
    private String isRocketShipping;
    @NotNull
    private String thumbnailUrl;
    @NotNull
    private String detailUrl;

    @Builder
    public ProductSaveRequestDto(Long type, String title, String company, int stock, int salesVolume, double price, int discountRate, double discountPrice, @NotNull String isGoldBox, @NotNull String isRocketShipping, String thumbnailUrl, String detailUrl) {
        this.type = type;
        this.title = title;
        this.company = company;
        this.stock = stock;
        this.salesVolume = salesVolume;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.isGoldBox = isGoldBox;
        this.isRocketShipping = isRocketShipping;
        this.thumbnailUrl = thumbnailUrl;
        this.detailUrl = detailUrl;
    }

    public Product toEntity(Category category){
        return Product.builder()
                .type(category)
                .title(this.title)
                .company(Company.valueOf(this.company))
                .stock(this.stock)
                .salesVolume(this.salesVolume)
                .price(this.price)
                .discountRate(this.discountRate)
                .discountPrice(this.discountPrice)
                .isGoldBox(Goldbox.valueOf(this.isGoldBox))
                .isRocketShipping(RocketShipping.valueOf(this.isRocketShipping))
                .thumbnailUrl(this.thumbnailUrl)
                .detailUrl(this.detailUrl)
                .build();
    }
}