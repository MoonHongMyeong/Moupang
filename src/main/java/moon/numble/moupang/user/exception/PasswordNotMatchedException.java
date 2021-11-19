package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class PasswordNotMatchedException extends InvalidValueException {

    public PasswordNotMatchedException(String message){
        super(message, ErrorCode.NOT_MATCHED_PASSWORD);
    }
}
