package moon.numble.moupang.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.order.domain.entity.OrderStep;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;

    private String userEmail;

    private List<OrderDetail> orderDetails;

    private double totalPrice;

    private OrderStep step;

    @Builder
    public OrderResponseDto(Long id, String userEmail, List<OrderDetail> orderDetails, double totalPrice, OrderStep step) {
        this.orderId = id;
        this.userEmail = userEmail;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
        this.step = step;
    }

    public static OrderResponseDto of(ProductOrder order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .userEmail(order.getUser().getEmail())
                .orderDetails(order.getOrderDetails())
                .totalPrice(order.getTotalPrice())
                .step(order.getOrderStep())
                .build();
    }
}
