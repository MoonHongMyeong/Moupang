package moon.numble.moupang.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderListSaveRequestDto {
    @NotNull
    private List<Long> cartIds;
    @NotNull
    private Long addressId;
}
