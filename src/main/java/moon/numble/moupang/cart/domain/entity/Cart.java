package moon.numble.moupang.cart.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_product_id",nullable = false)
    private Product product;

    @OneToOne
    @JoinColumn(name = "user_user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    private int quantity;

    @Builder
    public Cart(Product product, User user, CartStatus status, int quantity) {
        this.product = product;
        this.user = user;
        this.status = status;
        this.quantity = quantity;
    }

    public Cart updateQuantity(int newQuantity){
        this.quantity=newQuantity;
        return this;
    }
}
