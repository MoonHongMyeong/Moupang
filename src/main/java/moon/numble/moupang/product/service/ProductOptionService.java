package moon.numble.moupang.product.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.product.domain.entity.ClothesOption;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
import moon.numble.moupang.product.domain.repository.ClothesOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ClothesOptionRepository clothesOptionRepository;

    @Transactional(rollbackFor = Exception.class)
    public ProductOptionResponseDto create(ProductOptionSaveRequestDto dto, Long productId) {

        Product product = getProductById(productId);

        ProductOption productOption = productOptionRepository.save(dto.toEntity(product));

        product.addProductOption(productOption);

        return ProductOptionResponseDto.of(productOption);
    }

    private Product getProductById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty()){
            throw new EntityNotFoundException(productId.toString());
        }

        return product.get();
    }

    public ProductOptionResponseDto update(ProductOptionUpdateRequestDto dto, Long optionId) {
        ProductOption productOption = getProductOptionById(optionId);

        productOption.update(dto);

        return ProductOptionResponseDto.of(productOption);
    }

    private ProductOption getProductOptionById(Long optionId) {
        Optional<ProductOption> productOption = productOptionRepository.findById(optionId);

        if(productOption.isEmpty()){
            throw new EntityNotFoundException(optionId.toString());
        }

        return productOption.get();
    }

    public void deleteProductOption(Long optionId) {
        ProductOption productOption = getProductOptionById(optionId);

        productOptionRepository.delete(productOption);
    }

    @Transactional(rollbackFor = Exception.class)
    public ClothesOptionResponseDto create(ClothesOptionSaveRequestDto dto, Long productId){
        Product product = getProductById(productId);

        ClothesOption option = clothesOptionRepository.save(dto.toEntity(product));

        product.addClothesOption(option);

        return ClothesOptionResponseDto.of(option);
    }

    @Transactional(rollbackFor = Exception.class)
    public ClothesOptionResponseDto update(ClothesOptionUpdateRequestDto dto, Long optionId){
        ClothesOption option = getClothesOptionById(optionId);

        option.update(dto);

        return ClothesOptionResponseDto.of(option);
    }

    private ClothesOption getClothesOptionById(Long optionId) {
        Optional<ClothesOption> option = clothesOptionRepository.findById(optionId);

        if(option.isEmpty()){
            throw new EntityNotFoundException(optionId.toString());
        }

        return option.get();
    }

    public void deleteClothesOption(Long optionId) {
        ClothesOption option = getClothesOptionById(optionId);

        clothesOptionRepository.delete(option);
    }
}
