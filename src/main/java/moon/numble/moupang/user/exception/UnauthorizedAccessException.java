package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class UnauthorizedAccessException extends EntityNotFoundException {

    public UnauthorizedAccessException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_ACCESS);
    }
}
