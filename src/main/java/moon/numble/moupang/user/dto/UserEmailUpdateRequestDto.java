package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEmailUpdateRequestDto {
    private String email;

    @Builder
    public UserEmailUpdateRequestDto(String email){this.email=email;}

}
