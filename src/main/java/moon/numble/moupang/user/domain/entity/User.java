package moon.numble.moupang.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, String password, String name, String phone, Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public User updateEmail(String email){
        this.email = email;
        return this;
    }

    public User updatePassword(String password){
        this.password = password;
        return this;
    }

    public User updatePhone(String phone){
        this.phone = phone;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}