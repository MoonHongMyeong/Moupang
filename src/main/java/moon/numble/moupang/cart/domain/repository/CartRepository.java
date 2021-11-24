package moon.numble.moupang.cart.domain.repository;

import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long>, CartCustomRepository {
    Optional<Cart> findByProduct(Product product);
}
