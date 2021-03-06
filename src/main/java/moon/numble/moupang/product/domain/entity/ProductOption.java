package moon.numble.moupang.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.dto.ProductOptionUpdateRequestDto;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ProductOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_option_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column
    private String title;

    @Column
    private Double price;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cart_id", nullable = false, insertable = false, updatable = false)
    private List<Cart> cart;

    @Builder
    public ProductOption(Product product, String title, Double price) {
        this.product = product;
        this.title = title;
        this.price = price;
    }

    public void update(ProductOptionUpdateRequestDto dto){
        this.title=dto.getTitle();
        this.price=dto.getPrice();
    }
}
