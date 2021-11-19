package moon.numble.moupang.user.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.*;
import moon.numble.moupang.user.exception.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(UserSaveRequestDto saveRequestDto) {

        String encryptPassword = encodingPassword(saveRequestDto.getPassword());
        saveRequestDto.passwordEncryption(encryptPassword);

        User user = userRepository.save(saveRequestDto.toEntity());

        return UserResponseDto.of(user);
    }

    public void isDuplicateUserByPhone(String phone) {
        if(userRepository.existsByPhone(phone)){
            throw new PhoneDuplicateException(phone);
        }
    }

    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public SessionUser verifyUserLogin(UserLoginRequestDto loginRequestDto) {

        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

        if(user.isEmpty()){
            throw new LoginValidationException(loginRequestDto.getEmail());
        }

        User validateUser = user.get();
        boolean validatePassword = passwordEncoder.matches(loginRequestDto.getPassword(),validateUser.getPassword());

        if(!validatePassword){
            throw new LoginValidationException(loginRequestDto.getPassword());
        }

        return new SessionUser(validateUser);
    }

    private void checkUserPassword(String password, String userPassword) {
        boolean isValidPassword = passwordEncoder.matches(password, userPassword);

        if(!isValidPassword){
            throw new PasswordNotMatchedException(password);
        }
    }

    public User getUserToSessionUser(SessionUser sessionUser) {

        Optional<User> user = userRepository.findByEmail(sessionUser.getEmail());

        if(user.isEmpty()){
            throw new UserNotFoundException(sessionUser.getEmail());
        }

        return user.get();
    }

    public void verifyUser(SessionUser user, Long userId) {
        if(!user.getId().equals(userId)){
            throw new InvalidUserAccessException(userId.toString());
        }
    }

    public UserResponseDto updateUserEmail(SessionUser sessionUser, UserEmailUpdateRequestDto requestDto) {

        User user = getUserToSessionUser(sessionUser);
        user.updateEmail(requestDto.getEmail());

        return UserResponseDto.of(user);
    }

    public void isDuplicateUserByEmail(String email) {
        if(userRepository.existsByEmail(email)){
            throw new EmailDuplicateException(email);
        }
    }

    public UserResponseDto updateUserPassword(SessionUser sessionUser, UserPasswordUpdateRequestDto requestDto) {

        User user = getUserToSessionUser(sessionUser);
        String encryptOldPassword = encodingPassword(requestDto.getOldPassword());
        String encryptNewPassword = encodingPassword(requestDto.getNewPassword());

        checkUserPassword(requestDto.getOldPassword(), encryptOldPassword);

        user.updatePassword(encryptNewPassword);

        return UserResponseDto.of(user);
    }

    public UserResponseDto updateUserName(SessionUser sessionUser, UserNameUpdateRequestDto requestDto) {

        User user = getUserToSessionUser(sessionUser);
        user.updateName(requestDto.getName());

        return UserResponseDto.of(user);
    }

    public UserResponseDto updateUserPhone(SessionUser sessionUser, UserPhoneUpdateRequestDto requestDto) {

        User user = getUserToSessionUser(sessionUser);
        user.updatePhone(requestDto.getPhone());

        return UserResponseDto.of(user);
    }

    public void userWithdrawal(SessionUser sessionUser) {

        User user = getUserToSessionUser(sessionUser);

        userRepository.delete(user);
    }
}
