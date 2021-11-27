package moon.numble.moupang.posts.service;

import lombok.RequiredArgsConstructor;
import moon.numble.moupang.posts.domain.Repository.AttachFileRepository;
import moon.numble.moupang.posts.domain.entity.AttachFile;
import moon.numble.moupang.posts.domain.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileAttachService {
    private final AttachFileRepository attachFileRepository;

    public void saveReviewImages(List<String> files, Review review) {
        files.stream()
                .forEach(file -> attachFileRepository.save(AttachFile.builder()
                        .filePath(file)
                        .review(review)
                        .build()));
    }
}
