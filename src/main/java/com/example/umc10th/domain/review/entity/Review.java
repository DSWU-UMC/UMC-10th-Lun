package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id", nullable = false)
    private Long reviewId;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_reply_id")
    private ReviewReply reply;
}
