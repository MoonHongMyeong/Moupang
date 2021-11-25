package moon.numble.moupang.product.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.category.domain.entity.Category;
import moon.numble.moupang.category.domain.repository.CategoryRepository;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.domain.repository.ProductRepository;
import moon.numble.moupang.product.dto.ProductListResponseDto;
import moon.numble.moupang.product.dto.ProductResponseDto;
import moon.numble.moupang.product.dto.ProductSaveRequestDto;
import moon.numble.moupang.product.dto.ProductUpdateRequestDto;
import moon.numble.moupang.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> getAllProducts() {
        return productRepository.getAll().stream()
                .map(product -> ProductListResponseDto.of(product))
                .collect(Collectors.toList());
    }

    public ProductResponseDto getProductDetail(Long productId) {
        Product product = getProductById(productId);
        return ProductResponseDto.of(product);
    }

    public ProductResponseDto create(ProductSaveRequestDto dto) {

        Category category = getCategoryById(dto.getType());

        Product product = productRepository.save(dto.toEntity(category));

        return ProductResponseDto.of(product);
    }

    @Transactional(rollbackFor = Exception.class)
    public ProductResponseDto update(Long productsId, ProductUpdateRequestDto dto) {

        Category category = getCategoryById(dto.getType());
        Product product = getProductById(productsId);

        product.updateProductCategory(category);
        product.updateProduct(dto);

        return ProductResponseDto.of(product);
    }

    private Category getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isEmpty()){
            throw new EntityNotFoundException(categoryId.toString());
        }

        return category.get();
    }

    private Product getProductById(Long productsId) {
        Product product = productRepository.findProduct(productsId);

        if(product == null){
            throw new ProductNotFoundException(productsId.toString());
        }

        return product;
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long productsId) {

        Product product = getProductById(productsId);
        product.removeProduct();
    }
}
