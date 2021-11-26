package moon.numble.moupang.posts.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.order.service.OrderDetailSearchService;
import moon.numble.moupang.posts.domain.Repository.ReviewRepository;
import moon.numble.moupang.posts.dto.ReviewSaveRequestDto;
import moon.numble.moupang.posts.domain.entity.Review;
import moon.numble.moupang.user.domain.entity.User;
import moon.numble.moupang.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewPostService {

    private final UserService userService;
    private final OrderDetailSearchService orderDetailService;

    private final ReviewRepository reviewRepository;

    @Transactional(rollbackFor = Exception.class)
    public Review create(SessionUser sessionUser, ReviewSaveRequestDto dto, Long orderDetailId) {

        User user = userService.getUserToSessionUser(sessionUser);
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderDetailId);

        Review review = reviewRepository.save(Review.builder()
                        .orderDetail(orderDetail)
                        .user(user)
                        .starRate(dto.getStarRate())
                        .content(dto.getContent())
                        .summary(dto.getSummary())
                .build());

        return review;
    }

}
