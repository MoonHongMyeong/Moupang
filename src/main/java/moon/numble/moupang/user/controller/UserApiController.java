package moon.numble.moupang.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.user.annotation.LoginRequired;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.dto.*;
import moon.numble.moupang.user.service.LoginService;
import moon.numble.moupang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class UserApiController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> registrationCustomer(@RequestBody @Valid UserSaveRequestDto saveRequestDto){

        userService.isDuplicateUserByEmail(saveRequestDto.getEmail());
        userService.isDuplicateUserByPhone(saveRequestDto.getPhone());
        UserResponseDto dto = userService.registerUser(saveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @LoginRequired
    @GetMapping("/user")
    public ResponseEntity<UserResponseDto> getUserProfile(@LoginUser SessionUser user){
        return ResponseEntity.ok(UserResponseDto.of(user));
    }

    @LoginRequired
    @PutMapping("/user/{userId}/email")
    public ResponseEntity<UserResponseDto> updateUserEmail(@LoginUser SessionUser user,
                                                           @PathVariable(name = "userId") Long userId,
                                                           @RequestBody @Valid UserEmailUpdateRequestDto requestDto){

        userService.verifyUser(user, userId);
        userService.isDuplicateUserByEmail(requestDto.getEmail());

        UserResponseDto response = userService.updateUserEmail(user, requestDto);

        userLogout();

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @PutMapping("/user/{userId}/password")
    public ResponseEntity<UserResponseDto> updateUserPassword(@LoginUser SessionUser user,
                                                              @PathVariable(name = "userId") Long userId,
                                                              @RequestBody @Valid UserPasswordUpdateRequestDto requestDto){

        userService.verifyUser(user, userId);

        UserResponseDto response = userService.updateUserPassword(user, requestDto);

        userLogout();

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @PutMapping("/user/{userId}/name")
    public ResponseEntity<UserResponseDto> updateUserName(@LoginUser SessionUser user,
                                                          @PathVariable(name = "userId") Long userId,
                                                          @RequestBody @Valid UserNameUpdateRequestDto requestDto){

        userService.verifyUser(user, userId);

        UserResponseDto response = userService.updateUserName(user, requestDto);

        userLogout();

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @PutMapping("/user/{userId}/phone")
    public ResponseEntity<UserResponseDto> updateUserPhone(@LoginUser SessionUser user,
                                                           @PathVariable(name = "userId") Long userId,
                                                           @RequestBody @Valid UserPhoneUpdateRequestDto requestDto){

        userService.verifyUser(user, userId);

        UserResponseDto response = userService.updateUserPhone(user, requestDto);

        userLogout();

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> userWithdrawal(@LoginUser SessionUser user,
                                                     @PathVariable(name = "userId") Long userId){

        userService.verifyUser(user, userId);
        userService.userWithdrawal(user);

        userLogout();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<SessionUser> userLogin(@RequestBody @Valid UserLoginRequestDto loginRequestDto){

        SessionUser verifiedUser = userService.verifyUserLogin(loginRequestDto);

        loginService.login(verifiedUser);

        return ResponseEntity.ok(verifiedUser);
    }

    @LoginRequired
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> userLogout(){

        loginService.logout();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @LoginRequired
    @PostMapping("/user/{userId}/membership")
    public ResponseEntity<UserResponseDto> joinMembership(@LoginUser SessionUser sessionUser,
                                                          @PathVariable("userId") Long userId){
        userService.verifyUser(sessionUser, userId);

        User joinMember = userService.createMembership(sessionUser, userId);

        loginService.logout();
        loginService.login(new SessionUser(joinMember));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDto.of(joinMember));
    }

}
