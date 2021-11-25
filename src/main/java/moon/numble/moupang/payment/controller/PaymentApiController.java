package moon.numble.moupang.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.order.service.OrderService;
import moon.numble.moupang.payment.dto.PaymentResponseDto;
import moon.numble.moupang.payment.service.PaymentService;
import moon.numble.moupang.user.annotation.LoginRequired;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class PaymentApiController {

    private final UserService userService;
    private final PaymentService paymentService;

    @LoginRequired
    @GetMapping("/orders/{orderId}/payment")
    public ResponseEntity<PaymentResponseDto> pay(@RequestParam("paymentType") String paymentType,
                                                  @PathVariable("orderId") Long orderId,
                                                  @LoginUser SessionUser sessionUser){

        User user = userService.getUserToSessionUser(sessionUser);

        PaymentResponseDto response = paymentService.pay(orderId, user, paymentType);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
