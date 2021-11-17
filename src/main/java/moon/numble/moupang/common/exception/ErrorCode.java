package moon.numble.moupang.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //Common
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "알 수 없는 에러\n 관리자에게 연락해주세요."),
    INVALID_INPUT_VALUE(400, "Invalid Input Value", "잘못된 input 값 입니다."),
    ENTITY_NOT_FOUND(400,"Entity Not Found", "Entity 를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed", "허용되지 않은 메소드"),
    HANDLE_ACCESS_DENIED(403, "Handle Access Denied", "엑세스가 거부되었습니다."),
    INVALID_TYPE_VALUE(400, "Invalid Type Value", "잘못된 type 입니다."),
    //User
    EMAIL_DUPLICATION(409, "Email Duplication", "중복된 이메일입니다."),
    USER_NOT_FOUND(400, "User Not Found", "존재하지 않는 유저입니다."),
    INVALID_PASSWORD(401, "Invalid Password", "비밀번호를 틀렸습니다.")
    ;

    private final int status;
    private final String code;
    private final String message;


    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
