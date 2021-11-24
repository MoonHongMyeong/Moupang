package moon.numble.moupang.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.cart.dto.CartResponseDto;
import moon.numble.moupang.cart.dto.CartSaveRequestDto;
import moon.numble.moupang.cart.dto.CartUpdateRequestDto;
import moon.numble.moupang.cart.service.CartService;
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
public class CartApiController {

    private final UserService userService;
    private final CartService cartService;

    @LoginRequired
    @PostMapping("/products/{productId}/carts")
    public ResponseEntity<CartResponseDto> putInCart(@RequestBody @Valid CartSaveRequestDto dto,
                                                     @LoginUser SessionUser sessionUser,
                                                     @PathVariable("productId") Long productId){

        User user = userService.getUserToSessionUser(sessionUser);

        CartResponseDto response = cartService.createCartItem(dto, user, productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LoginRequired
    @PutMapping("/products/{productId}/carts/{cartId}")
    public ResponseEntity<CartResponseDto> updateCartItem(@RequestBody @Valid CartUpdateRequestDto dto,
                                                          @LoginUser SessionUser sessionUser,
                                                          @PathVariable("productId") Long productId,
                                                          @PathVariable("cartId") Long cartId){

        User user = userService.getUserToSessionUser(sessionUser);

        CartResponseDto response = cartService.updateCartItem(dto, cartId);

        return ResponseEntity.ok(response);
    }

    @LoginRequired
    @DeleteMapping("/products/{productId}/carts/{cartId}")
    public ResponseEntity<HttpStatus> deleteCartItem(@LoginUser SessionUser user,
                                                     @PathVariable("cartId") Long cartId){
        cartService.deleteCartItem(cartId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @LoginRequired
    @GetMapping("/user/{userId}/carts")
    public ResponseEntity<List<CartResponseDto>> getCartItems(@LoginUser SessionUser sessionUser,
                                                              @PathVariable("userId") Long userId){

        List<CartResponseDto> cartItems = cartService.getCartItems(userId);

        return ResponseEntity.ok(cartItems);
    }
}
