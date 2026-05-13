package com.example.umc10th.domain.review.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewResDTO {

    // 리뷰 조회
    @Builder
    public record GetReview (
            String storeName,
            String name,
            Float rating,
            String reviewContent,
            LocalDateTime createdAt
    ){}

    // 페이지네이션 틀(커서 기반)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
