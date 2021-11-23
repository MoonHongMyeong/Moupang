package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.ErrorCode;
import moon.numble.moupang.common.exception.InvalidValueException;

public class AlreadyMembershipUserException extends InvalidValueException {
    public AlreadyMembershipUserException(String email) {
        super(email+" is already membership", ErrorCode.ALREADY_MEMBERSHIP);
    }
}
