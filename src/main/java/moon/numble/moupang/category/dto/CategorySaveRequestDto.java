package moon.numble.moupang.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {
    @Nullable
    private Category parents;
    @NotEmpty
    private String name;

    @Builder
    public CategorySaveRequestDto(Category parents, String name) {
        this.parents = parents;
        this.name = name;
    }

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .parents(parents)
                .build();
    }
}
