package moon.numble.moupang.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchProductCondition {
    private String keyword;
    private String sort;
    private Double maxPrice;
    private Double minPrice;
    private Long categoryId;
    private String maker;
    private String isRocketShipping;

    @Builder
    public SearchProductCondition(String keyword, String sort, Double maxPrice, Double minPrice, Long categoryId, String maker, String isRocketShipping) {
        this.keyword = keyword;
        this.sort = sort;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.categoryId = categoryId;
        this.maker = maker;
        this.isRocketShipping = isRocketShipping;
    }
}
