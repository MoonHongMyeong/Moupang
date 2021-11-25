package moon.numble.moupang.product.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.dto.ClothesOptionUpdateRequestDto;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ClothesOption extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothes_option_id")
    private Long id;

    @Column
    private boolean isClothes;

    @Column
    private String size;

    @Column
    private String color;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
    private List<Cart> cart;


    @Builder
    public ClothesOption(boolean isClothes, String size, String color, Product product) {
        this.isClothes = isClothes;
        this.size = size;
        this.color = color;
        this.product = product;
    }

    public void update(ClothesOptionUpdateRequestDto dto){
        this.isClothes=dto.isClothes();
        this.size=dto.getSize();
        this.color=dto.getColor();
    }
}
