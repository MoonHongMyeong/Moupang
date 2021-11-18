package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserNameUpdateRequestDto {
    private String name;

    @Builder
    public UserNameUpdateRequestDto(String name){
        this.name=name;
    }
}
