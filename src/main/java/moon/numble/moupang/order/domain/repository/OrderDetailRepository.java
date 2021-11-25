package moon.numble.moupang.order.domain.repository;

import moon.numble.moupang.order.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
