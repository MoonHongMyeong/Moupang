package moon.numble.moupang.posts.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.posts.domain.Repository.ReviewRepository;
import moon.numble.moupang.posts.dto.ReviewResponseDto;
import moon.numble.moupang.posts.dto.ReviewSaveRequestDto;
import moon.numble.moupang.posts.domain.entity.Review;
import moon.numble.moupang.posts.dto.ReviewUpdateRequestDto;
import moon.numble.moupang.posts.service.FileAttachService;
import moon.numble.moupang.posts.service.FileUploadRequestService;
import moon.numble.moupang.posts.service.ReviewPostService;
import moon.numble.moupang.user.annotation.LoginRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ReviewApiController {

    private final ReviewPostService reviewService;
    private final FileAttachService fileAttachService;
    private final FileUploadRequestService uploadRequestService;

    @LoginRequired
    @PostMapping("/orderDetails/{orderDetailId}")
    public ResponseEntity<ReviewResponseDto> postReview(@LoginUser SessionUser user,
                                                        @RequestPart @Valid ReviewSaveRequestDto dto,
                                                        @RequestPart(required = false) List<MultipartFile> requestFiles,
                                                        @PathVariable("orderDetailId") Long orderDetailId){

        Review review = reviewService.create(user, dto, orderDetailId);

        List<String> files = uploadRequestService.uploadImages(requestFiles);

        fileAttachService.saveReviewImages(files, review);

        return ResponseEntity.status(HttpStatus.CREATED).body(ReviewResponseDto.of(review, files));
    }

    @LoginRequired
    @PutMapping("/products/{productId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(@LoginUser SessionUser user,
                                                          @RequestPart @Valid ReviewUpdateRequestDto dto,
                                                          @RequestPart(required = false) List<MultipartFile> requestFiles,
                                                          @PathVariable("reviewId") Long reviewId){

        Review review = reviewService.update(reviewId, dto);

        List<String> files = uploadRequestService.uploadImages(requestFiles);

        fileAttachService.saveReviewImages(files, review);

        return ResponseEntity.ok(ReviewResponseDto.of(review, files));
    }
}
