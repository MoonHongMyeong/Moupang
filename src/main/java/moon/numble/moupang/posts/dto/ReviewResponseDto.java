package moon.numble.moupang.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.posts.domain.entity.Review;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.ProductOption;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String userName;
    private String productName;
    private ProductOption optionName;
    private ClothesOption clothesOptionName;
    private int StarRate;
    private String content;
    private String summary;
    private List<String> images;

    @Builder
    public ReviewResponseDto(Long id, String userName, String productName, ProductOption optionName, ClothesOption clothesOptionName, int starRate, String content, String summary, List<String> images) {
        this.id = id;
        this.userName = userName;
        this.productName = productName;
        this.optionName = optionName;
        this.clothesOptionName = clothesOptionName;
        this.StarRate = starRate;
        this.content = content;
        this.summary = summary;
        this.images = images;
    }

    public static ReviewResponseDto of(Review review, List<String> files){
        return ReviewResponseDto.builder()
                .id(review.getId())
                .userName(review.getUser().getName())
                .productName(review.getOrderDetail().getCart().getProduct().getTitle())
                .optionName(review.getOrderDetail().getCart().getOption())
                .clothesOptionName(review.getOrderDetail().getCart().getClothes_option())
                .starRate(review.getStarRate())
                .content(review.getContent())
                .summary(review.getSummary())
                .images(files)
                .build();
    }

    public static ReviewResponseDto of(Review review){
        return ReviewResponseDto.builder()
                .id(review.getId())
                .userName(review.getUser().getName())
                .productName(review.getOrderDetail().getCart().getProduct().getTitle())
                .optionName(review.getOrderDetail().getCart().getOption())
                .clothesOptionName(review.getOrderDetail().getCart().getClothes_option())
                .starRate(review.getStarRate())
                .content(review.getContent())
                .summary(review.getSummary())
                .images(review.getAttachFiles().stream().map(attachFile -> attachFile.getFilePath()).collect(Collectors.toList()))
                .build();
    }
}
