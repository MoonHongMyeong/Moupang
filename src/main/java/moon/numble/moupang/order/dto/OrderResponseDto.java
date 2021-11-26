package moon.numble.moupang.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.OrderStep;
import moon.numble.moupang.order.domain.entity.ProductOrder;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {

    private Long orderId;

    private String userEmail;

    private ShippingAddress address;

    private List<OrderDetail> orderDetails;

    private double totalPrice;

    private OrderStep step;

    @Builder
    public OrderResponseDto(Long id, String userEmail, ShippingAddress address, List<OrderDetail> orderDetails, double totalPrice, OrderStep step) {
        this.orderId = id;
        this.userEmail = userEmail;
        this.address=address;
        this.orderDetails = orderDetails;
        this.totalPrice = totalPrice;
        this.step = step;
    }

    public static OrderResponseDto of(ProductOrder order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .userEmail(order.getUser().getEmail())
                .address(order.getAddress() )
                .orderDetails(order.getOrderDetails())
                .totalPrice(order.getTotalPrice())
                .step(order.getOrderStep())
                .build();
    }
}
