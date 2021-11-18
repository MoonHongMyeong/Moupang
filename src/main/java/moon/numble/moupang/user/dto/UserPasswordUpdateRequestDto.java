package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPasswordUpdateRequestDto {
    private String oldPassword;
    private String newPassword;

    @Builder
    public UserPasswordUpdateRequestDto(String oldPassword, String newPassword){
        this.oldPassword=oldPassword;
        this.newPassword=newPassword;
    }
}
