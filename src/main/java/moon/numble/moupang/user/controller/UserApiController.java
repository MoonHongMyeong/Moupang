package moon.numble.moupang.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.dto.UserLoginRequestDto;
import moon.numble.moupang.user.dto.UserResponseDto;
import moon.numble.moupang.user.dto.UserSaveRequestDto;
import moon.numble.moupang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class UserApiController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> registrationCustomer(@RequestBody UserSaveRequestDto saveRequestDto){

        userService.registerUser(saveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUserProfile(@LoginUser SessionUser user){
        return ResponseEntity.ok(UserResponseDto.of(user));
    }

    @PostMapping("/login")
    public ResponseEntity<SessionUser> userLogin(@RequestBody UserLoginRequestDto loginRequestDto){
        return ResponseEntity.ok(userService.loginUser(loginRequestDto));
    }

}
