package moon.numble.moupang.delivery.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.order.domain.entity.OrderDetail;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DeliveryResponseDto {
    private Long deliveryId;
    private OrderDetail orderDetail;
    private LocalDateTime completeDate;

    @Builder
    public DeliveryResponseDto(Long deliveryId, OrderDetail orderDetail, LocalDateTime completeDate) {
        this.deliveryId = deliveryId;
        this.orderDetail = orderDetail;
        this.completeDate = completeDate;
    }

    public static DeliveryResponseDto of(Delivery delivery){
        return DeliveryResponseDto.builder()
                .deliveryId(delivery.getId())
                .orderDetail(delivery.getOrderDetail())
                .completeDate(delivery.getCompleteDate())
                .build();
    }

}
