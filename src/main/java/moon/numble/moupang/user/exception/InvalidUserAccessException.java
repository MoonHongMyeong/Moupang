package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class InvalidUserAccessException extends InvalidValueException {

    public InvalidUserAccessException(String message) {
        super(message, ErrorCode.INVALID_USER_ACCESS);
    }

    public InvalidUserAccessException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
