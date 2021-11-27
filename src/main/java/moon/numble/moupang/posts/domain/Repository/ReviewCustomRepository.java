package moon.numble.moupang.posts.domain.Repository;

import moon.numble.moupang.posts.domain.entity.Review;

import java.util.List;

public interface ReviewCustomRepository {
    List<Review> findByProductId(Long productId);
}
