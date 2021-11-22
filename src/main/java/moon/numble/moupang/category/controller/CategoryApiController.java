package moon.numble.moupang.category.controller;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.category.dto.CategoryResponseDto;
import moon.numble.moupang.category.dto.CategorySaveRequestDto;
import moon.numble.moupang.category.dto.CategoryUpdateRequestDto;
import moon.numble.moupang.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryApiController {
    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){

        List<CategoryResponseDto> response = categoryService.getCategories();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody @Valid CategorySaveRequestDto dto){

        CategoryResponseDto response = categoryService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@RequestBody @Valid CategoryUpdateRequestDto dto,
                                                              @PathVariable("categoryId") Long categoryId){
        CategoryResponseDto response = categoryService.update(categoryId, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("categoryId") Long categoryId){

        categoryService.delete(categoryId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
