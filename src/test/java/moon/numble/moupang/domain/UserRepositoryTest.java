package moon.numble.moupang.domain;

import moon.numble.moupang.setup.TestProfile;
import moon.numble.moupang.user.domain.entity.Role;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(TestProfile.TEST)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User setUpUser;
    private String email;

    @BeforeAll
    public void setUp(){
        email = "moonhongmyeong@gmail.com";
        setUpUser = User.builder()
                .email(email)
                .name("moon")
                .password("12345")
                .phone("01011112222")
                .role(Role.CUSTOMER)
                .build();

        userRepository.save(setUpUser);
    }

    @AfterAll
    public void cleanUp(){
        userRepository.delete(setUpUser);
    }

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

    @Test
    @DisplayName("이메일이 존재하면 true")
    public void existsByEmailIsTrue(){
        final boolean existsByEmail = userRepository.existsByEmail(email);
        assertEquals(existsByEmail, true);
    }
}
