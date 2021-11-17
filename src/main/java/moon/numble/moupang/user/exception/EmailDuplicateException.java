package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class EmailDuplicateException extends InvalidValueException {

    public EmailDuplicateException(final String email){
        super(email + " is duplicated", ErrorCode.EMAIL_DUPLICATION);
    }

}
