package moon.numble.moupang.delivery.domain.repository;

import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.user.domain.entity.User;

import java.util.List;

public interface DeliveryCustomRepository {

    List<Delivery> getDeliveriesByUser(User user);

}
