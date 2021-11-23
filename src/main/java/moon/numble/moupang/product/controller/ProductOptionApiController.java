package moon.numble.moupang.product.controller;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.repository.ClothesOptionRepository;
import moon.numble.moupang.product.dto.*;
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

    @PostMapping("/products/{productId}/options")
    public ResponseEntity<ProductOptionResponseDto> createOption(@RequestBody @Valid ProductOptionSaveRequestDto dto,
                                                                 @PathVariable("productId") Long productId){

        ProductOptionResponseDto response = productOptionService.create(dto, productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/products/{productId}/options/{optionId}")
    public ResponseEntity<ProductOptionResponseDto> updateOption(@RequestBody @Valid ProductOptionUpdateRequestDto dto,
                                                                 @PathVariable("productId") Long productId,
                                                                 @PathVariable("optionId") Long optionId){
        ProductOptionResponseDto response = productOptionService.update(dto, optionId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/products/{productId}/options/{optionId}")
    public ResponseEntity<HttpStatus> deleteProductOption(@PathVariable("optionId") Long optionId){

        productOptionService.deleteProductOption(optionId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/products/{productId}/cOptions")
    public ResponseEntity<ClothesOptionResponseDto> createOption(@RequestBody@Valid ClothesOptionSaveRequestDto dto,
                                                                 @PathVariable("productId") Long productId){

        ClothesOptionResponseDto response = productOptionService.create(dto, productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/products/{productId}/cOptions/{optionId}")
    public ResponseEntity<ClothesOptionResponseDto> updateOption(@RequestBody @Valid ClothesOptionUpdateRequestDto dto,
                                                                 @PathVariable("optionId") Long optionId){

        ClothesOptionResponseDto response = productOptionService.update(dto, optionId);

        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/products/{productId}/cOptions/{optionId}")
//    public ResponseEntity<HttpStatus> deleteClothesOption(@PathVariable("optionId") Long optionId){
//        productOptionService.deleteClothesOption(optionId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
