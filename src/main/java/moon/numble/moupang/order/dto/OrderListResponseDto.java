package moon.numble.moupang.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.OrderStep;
import moon.numble.moupang.order.domain.entity.ProductOrder;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderListResponseDto {
    private Long orderId;
    private String orderer;
    private double totalPrice;
    private OrderStep orderStep;
    private List<OrderDetail> orderDetails;

    @Builder
    public OrderListResponseDto(Long orderId, String orderer, double totalPrice, OrderStep orderStep, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderer = orderer;
        this.totalPrice = totalPrice;
        this.orderStep = orderStep;
        this.orderDetails = orderDetails;
    }

    public static OrderListResponseDto of(ProductOrder order){
        return OrderListResponseDto.builder()
                .orderId(order.getId())
                .orderer(order.getUser().getEmail())
                .totalPrice(order.getTotalPrice())
                .orderStep(order.getOrderStep())
                .orderDetails(order.getOrderDetails())
                .build();
    }
}
