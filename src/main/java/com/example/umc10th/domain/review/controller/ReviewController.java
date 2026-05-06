package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<String> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview dto
            ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE_SUCCESS;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, dto));
    }
}
