package moon.numble.moupang.product.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class ProductSoldOutException extends InvalidValueException {
    public ProductSoldOutException(String title) {
        super(title, ErrorCode.PRODUCT_SOLD_OUT);
    }
}
