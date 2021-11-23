package moon.numble.moupang.product.controller;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.dto.*;
import moon.numble.moupang.product.service.ProductSearchService;
import moon.numble.moupang.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;
    private final ProductSearchService searchService;

    @GetMapping("/search/all")
    public ResponseEntity<List<ProductListResponseDto>> searchProducts(@RequestParam(name = "origQuery",required = false) String keyword,
                                                                       @RequestParam(name = "sort", required = false) String sort,
                                                                       @RequestParam(name = "maker", required = false) String maker,
                                                                       @RequestParam(name = "mixPrice", required = false) Double minPrice,
                                                                       @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                                                       @RequestParam(name = "catId",required = false) Long categoryId,
                                                                       @RequestParam(name = "isRocket",required = false) String isRocket){

        SearchProductCondition condition = SearchProductCondition.builder()
                .keyword(keyword)
                .sort(sort)
                .maker(maker)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .categoryId(categoryId)
                .isRocketShipping(isRocket)
                .build();

        List<ProductListResponseDto> response = searchService.search(condition);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductSaveRequestDto dto){
        ProductResponseDto response = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/products/{productsId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid ProductUpdateRequestDto dto,
                                                            @PathVariable("productsId") Long productsId){
        ProductResponseDto response = productService.update(productsId, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/products/{productsId}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("productsId") Long productsId){
        productService.delete(productsId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
