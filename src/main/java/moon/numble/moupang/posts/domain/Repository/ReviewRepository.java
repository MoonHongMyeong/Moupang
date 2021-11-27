package moon.numble.moupang.posts.domain.Repository;

import moon.numble.moupang.posts.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
}
