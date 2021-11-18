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

        isDuplicateUserByEmail(saveRequestDto.getEmail());

        String encryptPassword = encodingPassword(saveRequestDto.getPassword());
        saveRequestDto.passwordEncryption(encryptPassword);

        userRepository.save(saveRequestDto.toEntity());
    }

    private String encodingPassword(String password) {
        return passwordEncoder.encode(password);
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

    public UserResponseDto updateUserEmail(SessionUser sessionUser, Long userId, UserEmailUpdateRequestDto requestDto) {
        verifyUser(sessionUser, userId);

        isDuplicateUserByEmail(requestDto.getEmail());

        User user = isExistUserByEmail(sessionUser.getEmail());
        user.updateEmail(requestDto.getEmail());

        return UserResponseDto.of(user);
    }

    private void isDuplicateUserByEmail(String email) {
        if(userRepository.existsByEmail(email)){
            throw new EmailDuplicateException(email);
        }
    }

    public UserResponseDto updateUserPassword(SessionUser sessionUser, Long userId, UserPasswordUpdateRequestDto requestDto) {
        verifyUser(sessionUser, userId);

        User user = isExistUserByEmail(sessionUser.getEmail());

        checkUserPassword(requestDto.getOldPassword(), user.getPassword());

        String encryptPassword = encodingPassword(requestDto.getNewPassword());
        user.updatePassword(encryptPassword);

        return UserResponseDto.of(user);
    }

    public UserResponseDto updateUserName(SessionUser sessionUser, Long userId, UserNameUpdateRequestDto requestDto) {
        verifyUser(sessionUser, userId);

        User user = isExistUserByEmail(sessionUser.getEmail());
        user.updateName(requestDto.getName());

        return UserResponseDto.of(user);
    }

    public UserResponseDto updateUserPhone(SessionUser sessionUser, Long userId, UserPhoneUpdateRequestDto requestDto) {
        verifyUser(sessionUser, userId);

        User user = isExistUserByEmail(sessionUser.getEmail());
        user.updatePhone(requestDto.getPhone());

        return UserResponseDto.of(user);
    }

    public void userWithdrawal(SessionUser sessionUser, Long userId) {
        verifyUser(sessionUser, userId);

        User user = isExistUserByEmail(sessionUser.getEmail());

        userRepository.delete(user);
    }
}
