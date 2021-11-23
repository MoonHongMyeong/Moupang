package moon.numble.moupang.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.domain.entity.IsMembership;
import moon.numble.moupang.user.domain.entity.Membership;
import moon.numble.moupang.user.domain.entity.Role;
import moon.numble.moupang.user.domain.entity.User;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private IsMembership membership;
    private Role role;

    @Builder
    public UserResponseDto(Long id, String email, String name, String phone, IsMembership membership, Role role){
        this.id=id;
        this.email=email;
        this.name=name;
        this.phone=phone;
        this.membership=membership;
        this.role=role;
    }

    public static UserResponseDto of(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .membership(user.getMembership())
                .role(user.getRole())
                .build();
    }

    public static UserResponseDto of(SessionUser user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .membership(user.getMembership())
                .role(Enum.valueOf(Role.class, user.getRole()))
                .build();
    }
}
