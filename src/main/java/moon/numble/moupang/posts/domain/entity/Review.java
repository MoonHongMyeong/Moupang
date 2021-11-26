package moon.numble.moupang.posts.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moon.numble.moupang.common.BaseTimeEntity;
import moon.numble.moupang.order.domain.entity.OrderDetail;
import moon.numble.moupang.posts.dto.ReviewUpdateRequestDto;
import moon.numble.moupang.user.domain.entity.User;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    private int starRate;

    private String content;

    private String summary;

    @JsonIgnore
    @OneToMany(mappedBy = "review")
    private List<AttachFile> attachFiles;

    @Builder
    public Review(User user, OrderDetail orderDetail, int starRate, String content, String summary, List<AttachFile> attachFiles) {
        this.user = user;
        this.orderDetail = orderDetail;
        this.starRate = starRate;
        this.content = content;
        this.summary = summary;
        this.attachFiles = attachFiles;
    }

    public void updateReview(ReviewUpdateRequestDto dto) {
        this.starRate=dto.getStarRate();
        this.content=dto.getContent();
        this.summary= dto.getSummary();
    }
}
