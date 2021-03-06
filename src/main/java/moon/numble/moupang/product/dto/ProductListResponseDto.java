package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.RocketShipping;

@Getter
@NoArgsConstructor
public class ProductListResponseDto {

    private Long id;

    private String title;

    private String thumbnailUrl;

    private double price;

    private RocketShipping isRocketShipping;

    @Builder
    public ProductListResponseDto(Long id, String title, String thumbnailUrl, double price, RocketShipping isRocketShipping) {
        this.id = id;
        this.title=title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.isRocketShipping = isRocketShipping;
    }

    public static ProductListResponseDto of(Product product){
        return ProductListResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .thumbnailUrl(product.getThumbnailUrl())
                .price(product.getPrice())
                .isRocketShipping(product.getIsRocketShipping())
                .build();
    }
}
