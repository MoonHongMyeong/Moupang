package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductOptionSaveRequestDto {
    @NotNull
    private Product product;
    @NotEmpty
    private String title;
    @NotNull
    private Double price;

    @Builder
    public ProductOptionSaveRequestDto(Product product, String title, Double price) {
        this.product = product;
        this.title = title;
        this.price = price;
    }

    public ProductOption toEntity(Product product) {
        return ProductOption.builder()
                .product(product)
                .price(this.price)
                .title(this.title)
                .build();

    }
}
