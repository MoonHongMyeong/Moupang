package moon.numble.moupang.product.domain.repository;

import moon.numble.moupang.product.domain.entity.Product;

public interface ProductCustomRepository {
    Product findProduct(Long productId);
}
