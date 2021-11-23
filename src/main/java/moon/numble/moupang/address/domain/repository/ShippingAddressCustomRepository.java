package moon.numble.moupang.address.domain.repository;

import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.user.domain.entity.User;

import java.util.List;

public interface ShippingAddressCustomRepository {

    List<ShippingAddress> findAllUserAddress(User user);

}
