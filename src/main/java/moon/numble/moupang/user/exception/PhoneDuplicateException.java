package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class PhoneDuplicateException extends InvalidValueException {
    public PhoneDuplicateException(String phone) {
        super(phone, ErrorCode.PHONE_DUPLICATION);
    }
}
