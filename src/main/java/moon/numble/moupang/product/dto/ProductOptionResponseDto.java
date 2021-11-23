package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.product.domain.entity.ProductOption;

@Getter
@NoArgsConstructor
public class ProductOptionResponseDto {
    private Long id;
    private String title;
    private double price;

    @Builder
    public ProductOptionResponseDto(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public static ProductOptionResponseDto of(ProductOption productOption) {
        return ProductOptionResponseDto.builder()
                .id(productOption.getId())
                .title(productOption.getTitle())
                .price(productOption.getPrice())
                .build();
    }
}
