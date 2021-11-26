package moon.numble.moupang.delivery.domain.repository;

import moon.numble.moupang.delivery.domain.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryCustomRepository {
}
