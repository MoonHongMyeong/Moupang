package moon.numble.moupang.delivery.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.delivery.domain.entity.Delivery;
import moon.numble.moupang.user.domain.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static moon.numble.moupang.delivery.domain.entity.QDelivery.delivery;

@Transactional(readOnly = true)
public class DeliveryCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliveryCustomRepository{

    private final JPAQueryFactory queryFactory;

    public DeliveryCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Delivery.class);
        this.queryFactory=queryFactory;
    }

    @Override
    public List<Delivery> getDeliveriesByUser(User user) {
        return queryFactory.selectFrom(delivery)
                .where(delivery.user.eq(user))
                .orderBy(delivery.id.desc())
                .fetch();
    }
}
