package moon.numble.moupang.posts.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private int starRate;

    private String content;

    private String summary;

    @Builder
    public ReviewSaveRequestDto(int starRate, String content, String summary) {
        this.starRate = starRate;
        this.content = content;
        this.summary = summary;
    }
}
