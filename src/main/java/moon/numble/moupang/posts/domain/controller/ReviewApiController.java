package moon.numble.moupang.posts.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moon.numble.moupang.common.SessionUser;
import moon.numble.moupang.common.annotation.LoginUser;
import moon.numble.moupang.posts.domain.dto.ReviewResponseDto;
import moon.numble.moupang.posts.domain.dto.ReviewSaveRequestDto;
import moon.numble.moupang.posts.domain.entity.Review;
import moon.numble.moupang.posts.domain.service.FileAttachService;
import moon.numble.moupang.posts.domain.service.FileUploadRequestService;
import moon.numble.moupang.posts.domain.service.ReviewPostService;
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

        Review review = reviewService.postReview(user, dto, orderDetailId);

        List<String> files = uploadRequestService.uploadImages(requestFiles);

        fileAttachService.saveReviewImages(files, review);

        return ResponseEntity.status(HttpStatus.CREATED).body(ReviewResponseDto.of(review, files));
    }
}
