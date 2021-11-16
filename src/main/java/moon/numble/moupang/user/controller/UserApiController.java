package moon.numble.moupang.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        boolean isExistEmail = userService.isExistEmail(saveRequestDto.getEmail());

        if(isExistEmail){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        userService.registerUser(saveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
