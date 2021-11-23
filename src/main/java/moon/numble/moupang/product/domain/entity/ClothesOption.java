package moon.numble.moupang.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Builder
    public ClothesOption(boolean isClothes, String size, String color, Product product) {
        this.isClothes = isClothes;
        this.size = size;
        this.color = color;
        this.product = product;
    }
}
