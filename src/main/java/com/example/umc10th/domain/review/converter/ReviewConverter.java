package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;

public class ReviewConverter {

    // 리뷰 생성
    public static Review toCreateReview(ReviewReqDTO.CreateReview dto,User user, Store store) {
        return Review.builder()
                .reviewContent(dto.reviewContent())
                .rating(dto.rating())
                .store(store)
                .user(user)
                .build();
    }
}
