package moon.numble.moupang.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class OrderListSaveRequestDto {
    @NotNull
    private Long cartId;
}
