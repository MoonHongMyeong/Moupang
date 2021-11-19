package moon.numble.moupang.address.domain.repository;

import moon.numble.moupang.address.domain.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
}
