package moon.numble.moupang.common.exception;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(String message){
        super(message, ErrorCode.ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message, ErrorCode code) {
        super(message, code);
    }
}
