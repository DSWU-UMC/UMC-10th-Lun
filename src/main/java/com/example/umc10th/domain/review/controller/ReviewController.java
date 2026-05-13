package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
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
            @RequestBody @Valid ReviewReqDTO.CreateReview dto
            ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_CREATE_SUCCESS;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, dto));
    }

    // 리뷰 조회(커서 기반)
    @GetMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getReview(
        @PathVariable Long storeId,
        @RequestParam Integer pageSize,
        @RequestParam String cursor,
        @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_GET_SUCCESS;
        return ApiResponse.onSuccess(code, reviewService.getReview(storeId, pageSize, cursor, query));
    }
}
