package moon.numble.moupang.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserPhoneUpdateRequestDto {
    @NotBlank
    @Pattern(message = "'-'를 제외한 전화번호를 입력해주세요.",
            regexp ="\\d{10,11}")
    private String phone;
}
