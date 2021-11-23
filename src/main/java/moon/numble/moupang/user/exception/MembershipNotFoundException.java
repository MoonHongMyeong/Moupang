package moon.numble.moupang.user.exception;

import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.common.exception.ErrorCode;

public class MembershipNotFoundException extends EntityNotFoundException {
    public MembershipNotFoundException(String email) {
        super(email+" is not membership", ErrorCode.MEMBERSHIP_NOT_FOUND);
    }
}
