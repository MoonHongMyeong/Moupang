package moon.numble.moupang.address.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.address.domain.entity.ShippingMain;
import moon.numble.moupang.address.dto.ShippingAddressResponseDto;
import moon.numble.moupang.address.dto.ShippingAddressSaveRequestDto;
import moon.numble.moupang.address.dto.ShippingAddressUpdateRequestDto;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ShippingAddressApiController {
    private final ShippingAddressService addressService;
    private final UserService userService;

    @LoginRequired
    @GetMapping("/user/{userId}/address")
    public ResponseEntity<List<ShippingAddressResponseDto>> getAddresses(@LoginUser SessionUser sessionUser,
                                                                         @PathVariable("userId") Long userId){

        userService.verifyUser(sessionUser,userId);
        User user = userService.getUserToSessionUser(sessionUser);

        List<ShippingAddressResponseDto> response = addressService.getAddresses(user);

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @PostMapping("/user/{userId}/address")
    public ResponseEntity<ShippingAddressResponseDto> createAddress(@LoginUser SessionUser sessionUser,
                                                                    @PathVariable("userId") Long userId,
                                                                    @RequestBody @Valid ShippingAddressSaveRequestDto addressSaveRequestDto){

        userService.verifyUser(sessionUser,userId);
        User user = userService.getUserToSessionUser(sessionUser);

        if(addressSaveRequestDto.getMain() == ShippingMain.MAIN){
            addressService.allCancelMain(user);
        }

        ShippingAddressResponseDto response = addressService.create(user, addressSaveRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LoginRequired
    @PutMapping("/user/{userId}/address/{addressId}")
    public ResponseEntity<ShippingAddressResponseDto> updateAddress(@LoginUser SessionUser sessionUser,
                                                                    @PathVariable("userId") Long userId,
                                                                    @PathVariable("addressId") Long addressId,
                                                                    @RequestBody @Valid ShippingAddressUpdateRequestDto updateDto){

        userService.verifyUser(sessionUser,userId);
        User user = userService.getUserToSessionUser(sessionUser);

        if(updateDto.getMain().equals(ShippingMain.MAIN)){
            addressService.allCancelMain(user);
        }
        
        ShippingAddressResponseDto response = addressService.update(addressId, updateDto);

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @DeleteMapping("/user/{userId}/address/{addressId}")
    public ResponseEntity<HttpStatus> deleteAddress(@LoginUser SessionUser sessionUser,
                                                    @PathVariable("userId") Long userId,
                                                    @PathVariable("addressId") Long addressId){
        userService.verifyUser(sessionUser, userId);
        addressService.delete(addressId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
