package moon.numble.moupang.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.cart.dto.CartSaveRequestDto;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.order.dto.OrderListResponseDto;
import moon.numble.moupang.order.dto.OrderResponseDto;
import moon.numble.moupang.order.dto.OrderListSaveRequestDto;
import moon.numble.moupang.order.service.OrderService;
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
public class OrderApiController {

    private final OrderService orderService;
    private final UserService userService;

    @LoginRequired
    @PostMapping("/product/{productId}/purchase")
    public ResponseEntity<OrderResponseDto> directlyPurchase(@RequestBody @Valid CartSaveRequestDto dto,
                                                                 @LoginUser SessionUser sessionUser,
                                                                 @PathVariable("productId") Long productId){

        User user = userService.getUserToSessionUser(sessionUser);

        OrderResponseDto response = orderService.directlyPurchase(dto, user, productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @LoginRequired
//    @PostMapping("/user/{userId}/carts")
//    public ResponseEntity<OrderListResponseDto> purchaseFromCart(@RequestBody @Valid List<OrderListSaveRequestDto> dtos,
//                                                                   @LoginUser SessionUser sessionUser,
//                                                                   @PathVariable("userId") Long userId){
//
//        User user = userService.getUserToSessionUser(sessionUser);
//
//        OrderListResponseDto responses = orderService.purchaseFromCart(dtos, user);
//
//        return ResponseEntity.ok(responses);
//    }
}
