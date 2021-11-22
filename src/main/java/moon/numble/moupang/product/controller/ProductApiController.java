package moon.numble.moupang.product.controller;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.dto.ProductResponseDto;
import moon.numble.moupang.product.dto.ProductSaveRequestDto;
import moon.numble.moupang.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductSaveRequestDto dto){
        ProductResponseDto response = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
