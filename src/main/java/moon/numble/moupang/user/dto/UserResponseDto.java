package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.domain.entity.User;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String role;

    @Builder
    public UserResponseDto(Long id, String email, String name, String role){
        this.id=id;
        this.email=email;
        this.name=name;
        this.role=role;
    }

    public static UserResponseDto of(SessionUser user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
