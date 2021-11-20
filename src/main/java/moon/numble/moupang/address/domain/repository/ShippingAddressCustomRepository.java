package moon.numble.moupang.address.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.address.domain.entity.ShippingAddress;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static moon.numble.moupang.address.domain.entity.QShippingAddress.shippingAddress;

@Repository
public class ShippingAddressCustomRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ShippingAddressCustomRepository(JPAQueryFactory queryFactory) {
        super(ShippingAddress.class);
        this.queryFactory = queryFactory;
    }

    public List<ShippingAddress> findAllUserAddress(User user){
        return queryFactory.selectFrom(shippingAddress)
                .where(shippingAddress.user.eq(user))
                .orderBy(shippingAddress.main.asc())
                .fetch();
    }
}
