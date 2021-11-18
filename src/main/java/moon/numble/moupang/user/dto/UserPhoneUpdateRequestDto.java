package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPhoneUpdateRequestDto {
    private String phone;

    @Builder
    public UserPhoneUpdateRequestDto(String phone){
        this.phone=phone;
    }
}
