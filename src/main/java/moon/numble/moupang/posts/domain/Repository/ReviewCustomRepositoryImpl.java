package moon.numble.moupang.posts.domain.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import moon.numble.moupang.posts.domain.entity.Review;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static moon.numble.moupang.posts.domain.entity.QReview.review;

public class ReviewCustomRepositoryImpl extends QuerydslRepositorySupport implements ReviewCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Review.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Review> findByProductId(Long productId) {
        return queryFactory.selectFrom(review)
                .where(review.orderDetail.cart.product.id.eq(productId))
                .fetch();
    }
}
