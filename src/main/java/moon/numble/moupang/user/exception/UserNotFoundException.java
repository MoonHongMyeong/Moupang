package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String email) {
        super(email + " is not found", ErrorCode.USER_NOT_FOUND);
    }
}
