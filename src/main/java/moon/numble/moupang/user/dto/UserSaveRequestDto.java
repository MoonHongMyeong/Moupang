package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.user.domain.entity.Role;
import moon.numble.moupang.user.domain.entity.User;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String email;
    private String password;
    private String name;
    private String phone;
    private Role role = Role.CUSTOMER;

    @Builder
    public UserSaveRequestDto(String email, String password, String name, String phone){
        this.email=email;
        this.password=password;
        this.name=name;
        this.phone=phone;
    }

    public void passwordEncryption(String encryptedPassword){
        this.password = encryptedPassword;
    }

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .role(role)
                .build();
    }
}
