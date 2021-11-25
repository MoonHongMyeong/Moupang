package moon.numble.moupang.category.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.category.domain.repository.CategoryRepository;
import moon.numble.moupang.category.dto.CategoryResponseDto;
import moon.numble.moupang.category.dto.CategorySaveRequestDto;
import moon.numble.moupang.category.dto.CategoryUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getCategories(){

        return categoryRepository.findAll().stream()
                .map(category -> CategoryResponseDto.of(category))
                .collect(Collectors.toList());
    }

    public CategoryResponseDto create(CategorySaveRequestDto dto) {

        Category category = categoryRepository.save(dto.toEntity());

        return CategoryResponseDto.of(category);
    }

    public CategoryResponseDto update(Long categoryId, CategoryUpdateRequestDto dto) {

        Category category = categoryRepository.getById(categoryId);
        category.update(dto);

        return CategoryResponseDto.of(category);
    }

    public void delete(Long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        categoryRepository.delete(category);
    }
}
