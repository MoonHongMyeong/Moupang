package moon.numble.moupang.address.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.address.dto.ShippingAddressResponseDto;
import moon.numble.moupang.address.dto.ShippingAddressSaveRequestDto;
import moon.numble.moupang.address.service.ShippingAddressService;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.user.annotation.LoginRequired;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ShippingAddressApiController {
    private final ShippingAddressService addressService;
    private final UserService userService;

    @LoginRequired
    @PostMapping("/user/{userId}/address")
    public ResponseEntity<ShippingAddressResponseDto> createAddress(@LoginUser SessionUser sessionUser,
                                                                    @PathVariable("userId") Long userId,
                                                                    @RequestBody @Valid ShippingAddressSaveRequestDto addressSaveRequestDto){

        User user = userService.getUserToSessionUser(sessionUser);

        if(addressSaveRequestDto.getIsMain() == "MAIN"){
            addressService.allCancelMain(user);
        }

        ShippingAddressResponseDto response = addressService.create(user, addressSaveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
