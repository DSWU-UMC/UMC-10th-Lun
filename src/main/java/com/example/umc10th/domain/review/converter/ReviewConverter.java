package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;

import java.util.List;

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

    // 페이징 틀(커서 기반)
    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            boolean hasNext,
            String nextCursor,
            int pageSize
    ){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    // 리뷰 조회
    public static ReviewResDTO.GetReview toGetReview(Review review) {
        return ReviewResDTO.GetReview.builder()
                .name(review.getUser().getName())
                .reviewContent(review.getReviewContent())
                .storeName(review.getStore().getStoreName())
                .createdAt(review.getCreatedAt())
                .rating(review.getRating())
                .build();
    }
}
