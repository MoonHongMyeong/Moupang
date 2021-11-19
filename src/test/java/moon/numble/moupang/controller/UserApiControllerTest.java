package moon.numble.moupang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import moon.numble.moupang.MoupangApplication;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.setup.TestProfile;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.*;
import moon.numble.moupang.user.service.LoginService;
import moon.numble.moupang.user.service.SessionLoginService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MoupangApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
@ActiveProfiles(TestProfile.TEST)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserApiControllerTest{
    @Autowired
    protected MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private MockHttpSession mockHttpSession;
    private User setupUser;
    private SessionUser sessionUser;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email("testAdmin1@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112222")
                .build();

        setupUser = userRepository.save(dto.toEntity());
        sessionUser = new SessionUser(setupUser);

    }

    @AfterEach
    public void cleanup(){
        userRepository.delete(setupUser);
    }

    @Test
    public void 회원가입_성공() throws Exception {
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                    .email("testAdmin2@moupang.com")
                    .password("Hello1234!")
                    .name("TestUSER")
                    .phone("00022223333")
                    .build();

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("email").value(dto.getEmail()))
                .andExpect(jsonPath("name").value(dto.getName()))
                .andExpect(jsonPath("phone").value(dto.getPhone()));
    }

    @Test
    public void 중복된_이메일_가입시_예외() throws Exception {
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email(setupUser.getEmail())
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112223")
                .build();

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 중복된_전화번호_가입시_예외() throws Exception{
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email("testAdmin3@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone(setupUser.getPhone())
                .build();

        mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 로그인_상태가_아니면_회원조회에_실패한다() throws Exception {

        MockHttpSession session = new MockHttpSession();

        loginService.login(sessionUser);

        mvc.perform(get("/api/v1/user")
                        .session(session))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void 회원조회_성공() throws Exception {

       mockHttpSession = new MockHttpSession();
       loginService = new SessionLoginService(mockHttpSession);
       loginService.login(sessionUser);

       mvc.perform(get("/api/v1/user")
                       .session(mockHttpSession)
                       .sessionAttr("USER", sessionUser))
               .andDo(print())
               .andExpect(status().isOk());

       loginService.logout();
    }

    @Test
    public void 이메일_변경_성공() throws Exception{
        final String exceptedEmail = "exceptedEmail@moupang.com";
        final UserEmailUpdateRequestDto emailDto = new UserEmailUpdateRequestDto(exceptedEmail);

        mockHttpSession = new MockHttpSession();
        loginService = new SessionLoginService(mockHttpSession);
        loginService.login(sessionUser);

        mvc.perform(put("/api/v1/user/{id}/email", setupUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailDto))
                        .session(mockHttpSession)
                        .sessionAttr("USER", sessionUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value(exceptedEmail));
    }

    @Test
    public void 비밀번호_변경_성공() throws Exception{
        final String newPassword = "Hello4321!";
        final String oldPassword = setupUser.getPassword();
        UserPasswordUpdateRequestDto passwordDto = new UserPasswordUpdateRequestDto(oldPassword, newPassword);

        mockHttpSession = new MockHttpSession();
        loginService = new SessionLoginService(mockHttpSession);
        loginService.login(sessionUser);

        mvc.perform(put("/api/v1/user/{id}/password", setupUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(passwordDto))
                        .session(mockHttpSession)
                        .sessionAttr("USER", sessionUser))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 이름_변경_성공() throws Exception{
        final String exceptedName = "exceptedName";
        final UserNameUpdateRequestDto nameDto = new UserNameUpdateRequestDto(exceptedName);

        mockHttpSession = new MockHttpSession();
        loginService = new SessionLoginService(mockHttpSession);
        loginService.login(sessionUser);

        mvc.perform(put("/api/v1/user/{id}/name", setupUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nameDto))
                        .session(mockHttpSession)
                        .sessionAttr("USER", sessionUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(exceptedName));
    }

    @Test
    public void 전화번호_변경_성공() throws Exception{
        final String exceptedPhone = "11122223333";
        final UserPhoneUpdateRequestDto phoneDto = new UserPhoneUpdateRequestDto(exceptedPhone);

        mockHttpSession = new MockHttpSession();
        loginService = new SessionLoginService(mockHttpSession);
        loginService.login(sessionUser);

        mvc.perform(put("/api/v1/user/{id}/phone", setupUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(phoneDto))
                        .session(mockHttpSession)
                        .sessionAttr("USER", sessionUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("phone").value(exceptedPhone));
    }

    @Test
    public void 회원탈퇴_성공() throws Exception {
        mockHttpSession = new MockHttpSession();
        loginService = new SessionLoginService(mockHttpSession);
        loginService.login(sessionUser);

        mvc.perform(delete("/api/v1/user/{id}", setupUser.getId())
                .session(mockHttpSession)
                .sessionAttr("USER", sessionUser))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
