package com.example.umc10th.domain.review.dto;

import lombok.Data;
import java.util.List;

@Data
public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(
            Long userId,
            String reviewContent,
            Float rating,
            List<String> photos
    ){}
}
