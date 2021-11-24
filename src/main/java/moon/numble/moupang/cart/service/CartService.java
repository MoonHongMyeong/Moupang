package moon.numble.moupang.cart.service;

import moon.numble.moupang.cart.domain.entity.Cart;

public interface CartService {
    Cart getCartById(Long cartId);
}
