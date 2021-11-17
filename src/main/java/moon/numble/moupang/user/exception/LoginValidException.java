package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class LoginValidException extends InvalidValueException {
    public LoginValidException(String message){
        super(message, ErrorCode.INVALID_PASSWORD);
    }
}
