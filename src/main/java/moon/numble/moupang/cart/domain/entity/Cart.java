package moon.numble.moupang.cart.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
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
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption option;

    @ManyToOne
    @JoinColumn(name = "clothes_option_id")
    private ClothesOption clothes_option;

    @Builder
    public Cart(Product product, User user, CartStatus status, int quantity, ProductOption option, ClothesOption clothes_option) {
        this.product = product;
        this.user = user;
        this.status = status;
        this.quantity = quantity;
        this.option = option;
        this.clothes_option = clothes_option;
    }

    public Cart updateQuantity(int newQuantity){
        this.quantity=newQuantity;
        return this;
    }
}
