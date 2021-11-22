package moon.numble.moupang.category.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.dto.CategoryUpdateRequestDto;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.product.domain.entity.Product;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parents;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "parents", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products;


    @Builder
    public Category(Category parents, String name) {
        this.parents = parents;
        this.name = name;
    }

    public void update(CategoryUpdateRequestDto dto){
        this.parents = dto.getParents();
        this.name = dto.getName();
    }
}
