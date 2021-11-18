package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class LoginValidationException extends InvalidValueException {
    public LoginValidationException(String value) {
        super(value, ErrorCode.LOGIN_VALIDATION);
    }
}
