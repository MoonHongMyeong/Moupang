package moon.numble.moupang.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {

    private int starRate;

    private String content;

    private String summary;

    @Builder
    public ReviewUpdateRequestDto(int starRate, String content, String summary) {
        this.starRate = starRate;
        this.content = content;
        this.summary = summary;
    }
}
