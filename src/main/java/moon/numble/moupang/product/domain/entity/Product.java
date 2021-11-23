package moon.numble.moupang.product.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.dto.ProductUpdateRequestDto;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category type;

    @Column
    private String title;

    @Column
    private Company company;

    @Column
    private int stock;

    @Column
    private int salesVolume;

    @Column
    private double price;

    @Column
    private int discountRate;

    @Column
    private double discountPrice;

    @Column
    private Goldbox isGoldBox;

    @Column
    private RocketShipping isRocketShipping;

    @Column
    private String thumbnailUrl;

    @Column
    private String detailUrl;

    @Column
    private Integer isDeleted;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductOption> productOptions;

    @OneToOne
    private ClothesOption clothesOption;

    @Builder
    public Product(Category type, String title, Company company, int stock, int salesVolume, double price, int discountRate, double discountPrice, Goldbox isGoldBox, RocketShipping isRocketShipping, String thumbnailUrl, String detailUrl, Integer isDeleted) {
        this.type = type;
        this.title = title;
        this.company = company;
        this.stock = stock;
        this.salesVolume = salesVolume;
        this.price = price;
        this.discountRate = discountRate;
        this.discountPrice = discountPrice;
        this.isGoldBox = isGoldBox;
        this.isRocketShipping = isRocketShipping;
        this.thumbnailUrl = thumbnailUrl;
        this.detailUrl = detailUrl;
        this.isDeleted = 0;
    }

    public Product updateProduct(ProductUpdateRequestDto dto) {
        return Product.builder()
                .title(dto.getTitle())
                .company(Company.valueOf(dto.getCompany()))
                .stock(dto.getStock())
                .salesVolume(dto.getSalesVolume())
                .price(dto.getPrice())
                .discountRate(dto.getDiscountRate())
                .discountPrice(dto.getDiscountPrice())
                .isGoldBox(Goldbox.valueOf(dto.getIsGoldBox()))
                .isRocketShipping(RocketShipping.valueOf(dto.getIsRocketShipping()))
                .thumbnailUrl(dto.getThumbnailUrl())
                .detailUrl(dto.getDetailUrl())
                .build();
    }

    public void updateProductCategory(Category category){
        this.type=category;
    }

    public Product purchaseProduct(){
        return Product.builder()
                .stock(this.stock-1)
                .salesVolume(this.salesVolume+1)
                .build();
    }

    public Product removeProduct(){
        return Product.builder()
                .isDeleted(1)
                .build();
    }

}
