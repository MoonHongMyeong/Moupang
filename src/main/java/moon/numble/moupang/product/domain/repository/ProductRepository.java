package moon.numble.moupang.product.domain.repository;

import moon.numble.moupang.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
