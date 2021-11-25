package moon.numble.moupang.order.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class OrderNotFoundException extends EntityNotFoundException {
    public OrderNotFoundException(String orderId) {
        super(orderId+" is not found", ErrorCode.ORDER_NOT_FOUND);
    }
}
