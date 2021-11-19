package moon.numble.moupang.user.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(targetEntity = ShippingAddress.class, mappedBy = "user", cascade = CascadeType.ALL)
    private List<ShippingAddress> shippingAddresses;

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

    public User updateName(String name){
        this.name=name;
        return this;
    }

    public User updatePhone(String phone){
        this.phone = phone;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

    public String getRoleKr(){
        return this.role.getValue();
    }
}
