package moon.numble.moupang.delivery.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Delivery extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderDetail orderDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime completeDate;

    @Builder
    public Delivery(OrderDetail orderDetail, User user, LocalDateTime completeDate) {
        this.orderDetail = orderDetail;
        this.user = user;
        this.completeDate = completeDate;
    }
}
