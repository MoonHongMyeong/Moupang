package moon.numble.moupang.cart.domain.repository;

import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.user.domain.entity.User;

import java.util.List;

public interface CartCustomRepository {
    List<Cart> getCartItems(User user);
}
