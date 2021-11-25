package moon.numble.moupang.product.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class OrderQuantityMuchStockException extends InvalidValueException {
    public OrderQuantityMuchStockException(String title) {
        super(title, ErrorCode.QUANTITY_MUCH_STOCK);
    }
}
