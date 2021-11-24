package moon.numble.moupang.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ProductOrder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrice;

    private OrderStep orderStep;

    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @Builder
    public ProductOrder(User user, double totalPrice, OrderStep orderStep) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderStep = orderStep;
    }

    public void orderCancel(){
        this.orderStep = OrderStep.CANCEL;
    }

    public void orderComplete(double totalPrice){
        this.totalPrice=totalPrice;
        this.orderStep=OrderStep.ORDER;
    }

    public void inputDetail(List<OrderDetail> details){
        this.orderDetails = details;
    }
}
