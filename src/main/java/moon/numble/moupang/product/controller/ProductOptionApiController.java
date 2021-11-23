package moon.numble.moupang.product.controller;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.dto.ProductOptionResponseDto;
import moon.numble.moupang.product.dto.ProductOptionSaveRequestDto;
import moon.numble.moupang.product.dto.ProductOptionUpdateRequestDto;
import moon.numble.moupang.product.service.ProductOptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductOptionApiController {
    private final ProductOptionService productOptionService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<ProductOptionResponseDto> createOption(@RequestBody @Valid ProductOptionSaveRequestDto dto,
                                                                 @PathVariable("productId") Long productId){

        ProductOptionResponseDto response = productOptionService.create(dto, productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/product/{productId}/options/{optionId}")
    public ResponseEntity<ProductOptionResponseDto> updateOption(@RequestBody @Valid ProductOptionUpdateRequestDto dto,
                                                                 @PathVariable("productId") Long productId,
                                                                 @PathVariable("optionId") Long optionId){
        ProductOptionResponseDto response = productOptionService.update(dto, optionId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/product/{productId}/options/{optionId}")
    public ResponseEntity<HttpStatus> deleteOption(@PathVariable("optionId") Long optionId){

        productOptionService.delete(optionId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
