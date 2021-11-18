package moon.numble.moupang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.setup.TestProfile;
import moon.numble.moupang.user.controller.UserApiController;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.UserEmailUpdateRequestDto;
import moon.numble.moupang.user.dto.UserSaveRequestDto;
import moon.numble.moupang.user.exception.EmailDuplicateException;
import moon.numble.moupang.user.exception.PasswordNotMatchedException;
import moon.numble.moupang.user.service.LoginService;
import moon.numble.moupang.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserApiController.class)
@AutoConfigureMockMvc
@ActiveProfiles(TestProfile.TEST)
public class UserApiControllerTest{
    @Autowired
    protected MockMvc mvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private LoginService loginService;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserRepository userRepository;

    private User user;
    private UserEmailUpdateRequestDto updateRequestDto;

    @Test
    public void 회원가입_성공() throws Exception {
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                    .email("testAdmin2@moupang.com")
                    .password("Hello1234!")
                    .name("TestUSER")
                    .phone("00011112222")
                    .build();

        doNothing().when(userService).registerUser(dto);

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void 중복된_이메일_가입시_예외() throws Exception {
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email("testAdmin2@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112222")
                .build();

        doThrow(new EmailDuplicateException(dto.getEmail())).when(userService).registerUser(any());

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 중복된_전화번호_가입시_예외() throws Exception{
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email("testAdmin2@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112222")
                .build();

        doThrow(new PasswordNotMatchedException(dto.getPhone())).when(userService).registerUser(any());

        mvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void 로그인_상태가_아니면_회원조회에_실패한다() throws Exception {

        MockHttpSession session = new MockHttpSession();

        mvc.perform(get("/api/v1/user")
                        .session(session))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void 회원조회_성공() throws Exception {
        final UserSaveRequestDto dto = UserSaveRequestDto.builder()
                .email("testAdmin2@moupang.com")
                .password("Hello1234!")
                .name("TestUSER")
                .phone("00011112222")
                .build();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("USER", new SessionUser(dto.toEntity()));

        mvc.perform(get("/api/v1/user")
                        .session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
