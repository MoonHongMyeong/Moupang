package moon.numble.moupang.posts.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class AttachFile extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attach_file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private String filePath;

    @Builder
    public AttachFile(Review review, String filePath) {
        this.review = review;
        this.filePath = filePath;
    }

    public AttachFile(MultipartFile file){
        this.filePath=file.getOriginalFilename();
    }
}
