package moon.numble.moupang.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DirectOrderRequestDto {
    @NotEmpty
    private String status;
    @NotNull
    private int quantity;
    @NotNull
    private Long productOptionId;
    @NotNull
    private Long clothesOptionId;
    @NotNull
    private Long shippingAddressId;

    @Builder
    public DirectOrderRequestDto(String status, int quantity, Long productOptionId, Long clothesOptionId, Long shippingAddressId) {
        this.status = status;
        this.quantity = quantity;
        this.productOptionId=productOptionId;
        this.clothesOptionId=clothesOptionId;
        this.shippingAddressId=shippingAddressId;
    }
}
