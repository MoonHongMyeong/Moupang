package moon.numble.moupang.user.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.*;
import moon.numble.moupang.user.exception.EmailDuplicateException;
import moon.numble.moupang.user.exception.InvalidUserAccessException;
import moon.numble.moupang.user.exception.PasswordNotMatchedException;
import moon.numble.moupang.user.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
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

    public SessionUser verifyUserLogin(UserLoginRequestDto loginRequestDto) {

        User user = isExistUserByEmail(loginRequestDto.getEmail());

        checkUserPassword(loginRequestDto.getPassword(),user.getPassword());

        return new SessionUser(user);
    }

    private void checkUserPassword(String password, String userPassword) {
        boolean isValidPassword = passwordEncoder.matches(password, userPassword);

        if(!isValidPassword){
            throw new PasswordNotMatchedException(password);
        }
    }

    private User isExistUserByEmail(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            throw new UserNotFoundException(email);
        }

        return user.get();
    }

    private void verifyUser(SessionUser user, Long userId) {
        if(!user.getId().equals(userId)){
            throw new InvalidUserAccessException(userId.toString());
        }
    }
}
