package moon.numble.moupang.user.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.user.domain.repository.UserRepository;
import moon.numble.moupang.user.dto.UserSaveRequestDto;
import moon.numble.moupang.user.exception.EmailDuplicateException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

}
