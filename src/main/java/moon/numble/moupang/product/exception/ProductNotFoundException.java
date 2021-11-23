package moon.numble.moupang.product.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String productId) {
        super(productId + "is not found", ErrorCode.PRODUCT_NOT_FOUND);
    }

    public ProductNotFoundException(String message, ErrorCode code) {
        super(message, code);
    }
}
