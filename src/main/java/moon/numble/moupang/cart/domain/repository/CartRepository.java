package moon.numble.moupang.cart.domain.repository;

import moon.numble.moupang.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
