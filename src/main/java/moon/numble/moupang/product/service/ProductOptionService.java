package moon.numble.moupang.product.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.entity.ProductOption;
import moon.numble.moupang.product.domain.repository.ProductOptionRepository;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.dto.ProductOptionResponseDto;
import moon.numble.moupang.product.dto.ProductOptionSaveRequestDto;
import moon.numble.moupang.product.dto.ProductOptionUpdateRequestDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public ProductOptionResponseDto create(ProductOptionSaveRequestDto dto, Long productId) {

        Product product = getProductById(productId);

        ProductOption productOption = productOptionRepository.save(dto.toEntity(product));

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

    public void delete(Long optionId) {
        ProductOption productOption = getProductOptionById(optionId);

        productOptionRepository.delete(productOption);
    }
}
