package moon.numble.moupang.product.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.dto.ProductResponseDto;
import moon.numble.moupang.product.dto.ProductSaveRequestDto;
import moon.numble.moupang.product.dto.ProductUpdateRequestDto;
import moon.numble.moupang.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDto create(ProductSaveRequestDto dto) {
        Product product = productRepository.save(dto.toEntity());
        return ProductResponseDto.of(product);
    }

    public ProductResponseDto update(Long productsId, ProductUpdateRequestDto dto) {

        Product product = getProductById(productsId);
        product.updateProduct(dto);

        return ProductResponseDto.of(product);
    }

    private Product getProductById(Long productsId) {
        Optional<Product> product = productRepository.findById(productsId);

        if(product.isEmpty()){
            throw new ProductNotFoundException(productsId.toString());
        }

        return product.get();
    }

}
