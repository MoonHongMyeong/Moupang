package moon.numble.moupang.cart.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.cart.domain.entity.Cart;
import moon.numble.moupang.cart.domain.entity.CartStatus;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static moon.numble.moupang.cart.domain.entity.QCart.cart;

@Transactional(readOnly = true)
public class CartCustomRepositoryImpl extends QuerydslRepositorySupport implements CartCustomRepository{

    private JPAQueryFactory queryFactory;

    public CartCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Cart.class);
        this.queryFactory=queryFactory;
    }

    @Override
    public List<Cart> getCartItems(User user) {
        return queryFactory.selectFrom(cart)
                .where(cart.user.eq(user), cart.status.eq(CartStatus.CART))
                .fetch();
    }
}
