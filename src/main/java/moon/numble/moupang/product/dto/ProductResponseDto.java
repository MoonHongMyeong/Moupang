package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.product.domain.entity.Company;
import moon.numble.moupang.product.domain.entity.Goldbox;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.RocketShipping;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;

    private Category type;

    private String title;

    private Company company;

    private double price;

    private Goldbox isGoldBox;

    private RocketShipping isRocketShipping;

    private String thumbnailUrl;

    private String detailUrl;

    @Builder
    public ProductResponseDto(Long id, Category type, String title, Company company, double price, Goldbox isGoldBox, RocketShipping isRocketShipping, String thumbnailUrl, String detailUrl) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.company = company;
        this.price = price;
        this.isGoldBox = isGoldBox;
        this.isRocketShipping = isRocketShipping;
        this.thumbnailUrl = thumbnailUrl;
        this.detailUrl = detailUrl;
    }

    public static ProductResponseDto of(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .type(product.getType())
                .title(product.getTitle())
                .company(product.getCompany())
                .price(product.getPrice())
                .isGoldBox(product.getIsGoldBox())
                .isRocketShipping(product.getIsRocketShipping())
                .thumbnailUrl(product.getThumbnailUrl())
                .detailUrl(product.getDetailUrl())
                .build();
    }
}
