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
    private Category type;
    @NotEmpty
    private String title;
    @NotNull
    private Company company;
    @NotEmpty
    private int stock;
    @NotEmpty
    private int salesVolume;
    @NotEmpty
    private double price;
    @Nullable
    private int discountRate;
    @Nullable
    private double discountPrice;
    @NotNull
    private Goldbox isGoldBox;
    @NotNull
    private RocketShipping isRocketShipping;
    @NotNull
    private String thumbnailUrl;
    @NotNull
    private String detailUrl;

    @Builder
    public ProductSaveRequestDto(Category type, String title, Company company, int stock, int salesVolume, double price, int discountRate, double discountPrice, Goldbox isGoldBox, RocketShipping isRocketShipping, String thumbnailUrl, String detailUrl) {
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

    public Product toEntity(){
        return Product.builder()
                .type(this.type)
                .title(this.title)
                .company(this.company)
                .stock(this.stock)
                .salesVolume(this.salesVolume)
                .price(this.price)
                .discountRate(this.discountRate)
                .discountPrice(this.discountPrice)
                .isGoldBox(this.isGoldBox)
                .isRocketShipping(this.isRocketShipping)
                .thumbnailUrl(this.thumbnailUrl)
                .detailUrl(this.detailUrl)
                .build();
    }
}