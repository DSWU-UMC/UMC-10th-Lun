package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class ReviewReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_reply_id", nullable = false)
    private Long replyId;

    @Column(name = "reply_content", nullable = false)
    private String replyContent;

    @CreatedDate
    @Column(name= "created_at")
    private LocalDate createdAt;
}
