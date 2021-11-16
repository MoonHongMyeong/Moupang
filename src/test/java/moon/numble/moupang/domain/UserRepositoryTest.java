package moon.numble.moupang.domain;

import moon.numble.moupang.user.domain.entity.Role;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("userRepository null 이 아니다.")
    public void userRepositoryIsNotNull(){
        assertNotNull(userRepository);
    }

    @Test
    @DisplayName("회원 가입에 성공한다.")
    public void userRegistration(){
        //given
        User user = User.builder()
                .email("test@test.com")
                .name("test user")
                .password("testPassword")
                .phone("00000000000")
                .role(Role.CUSTOMER)
                .build();
        //when
        User resultUser = userRepository.save(user);
        //then
        assertNotNull(resultUser.getId());
        assertEquals(resultUser.getEmail(), "test@test.com");
    }
}
