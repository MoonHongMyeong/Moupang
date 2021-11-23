package moon.numble.moupang.product.domain.repository;

import moon.numble.moupang.product.domain.entity.Product;
import moon.numble.moupang.product.dto.SearchProductCondition;

import java.util.List;

public interface ProductSearchCustomRepository {
    List<Product> search(SearchProductCondition condition);
    List<Product> getAll();
}
