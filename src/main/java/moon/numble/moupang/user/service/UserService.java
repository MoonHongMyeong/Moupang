package moon.numble.moupang.user.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.UserLoginRequestDto;
import moon.numble.moupang.user.dto.UserSaveRequestDto;
import moon.numble.moupang.user.exception.EmailDuplicateException;
import moon.numble.moupang.user.exception.LoginValidException;
import moon.numble.moupang.user.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final LoginService loginService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserSaveRequestDto saveRequestDto) {

        if(userRepository.existsByEmail(saveRequestDto.getEmail())){
            throw new EmailDuplicateException(saveRequestDto.getEmail());
        }

        String encryptPassword = passwordEncoder.encode(saveRequestDto.getPassword());
        saveRequestDto.passwordEncryption(encryptPassword);

        userRepository.save(saveRequestDto.toEntity());
    }

    public SessionUser loginUser(UserLoginRequestDto loginRequestDto) {

        boolean isExistUser = userRepository.existsByEmail(loginRequestDto.getEmail());

        if(!isExistUser){
            throw new UserNotFoundException(loginRequestDto.getEmail());
        }

        User user = userRepository.findByEmail(loginRequestDto.getEmail());

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new LoginValidException(loginRequestDto.getPassword());
        }

        SessionUser sessionUser = new SessionUser(user);

        loginService.login(sessionUser);

        return sessionUser;
    }
}
