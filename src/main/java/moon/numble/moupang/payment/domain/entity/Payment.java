package moon.numble.moupang.payment.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.order.domain.entity.ProductOrder;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Builder
    public Payment(ProductOrder order, User user, PaymentType paymentType) {
        this.order = order;
        this.user = user;
        this.paymentType=paymentType;
    }
}
