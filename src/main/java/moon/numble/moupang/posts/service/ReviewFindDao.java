package moon.numble.moupang.posts.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.exception.EntityNotFoundException;
import moon.numble.moupang.posts.domain.Repository.ReviewRepository;
import moon.numble.moupang.posts.domain.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewFindDao {

    private final ReviewRepository reviewRepository;


    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()-> new EntityNotFoundException(reviewId.toString()));
    }
}
