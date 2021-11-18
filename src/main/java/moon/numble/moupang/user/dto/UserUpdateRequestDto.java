package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String name;
    private String phone;

    @Builder
    public UserUpdateRequestDto(String email, String oldPassword, String newPassword, String name, String phone){
        this.email=email;
        this.oldPassword=oldPassword;
        this.newPassword=newPassword;
        this.name=name;
        this.phone=phone;
    }
}
