package moon.numble.moupang.address.domain.repository;

import moon.numble.moupang.address.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
