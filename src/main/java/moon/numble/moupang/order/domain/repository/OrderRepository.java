package moon.numble.moupang.order.domain.repository;

import moon.numble.moupang.order.domain.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
}
