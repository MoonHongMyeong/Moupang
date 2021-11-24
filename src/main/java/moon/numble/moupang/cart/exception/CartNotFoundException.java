package moon.numble.moupang.cart.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class CartNotFoundException extends EntityNotFoundException {
    public CartNotFoundException(String cartId) {
        super(cartId, ErrorCode.CART_NOT_FOUND);
    }
}
