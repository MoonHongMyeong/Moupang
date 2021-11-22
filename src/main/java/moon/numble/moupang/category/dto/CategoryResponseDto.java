package moon.numble.moupang.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private Category parents;
    private String name;

    @Builder
    public CategoryResponseDto(Long id, Category parents, String name) {
        this.id = id;
        this.parents = parents;
        this.name = name;
    }

    public static CategoryResponseDto of(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .parents(category.getParents())
                .name(category.getName())
                .build();
    }
}
