package moon.numble.moupang.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.common.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderDetail extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder order;

    private int quantity;

    private double calculatePrice;

    private double shippingFee;

    @Builder
    public OrderDetail(Cart cart, ProductOrder order, int quantity, double calculatePrice, double shippingFee) {
        this.cart = cart;
        this.order = order;
        this.quantity = quantity;
        this.calculatePrice = calculatePrice;
        this.shippingFee = shippingFee;
    }
}
