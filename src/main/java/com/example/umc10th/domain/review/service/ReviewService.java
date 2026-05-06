package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    public String createReview(Long storeId, ReviewReqDTO.CreateReview dto) {
        // StoreId 추출
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // 리뷰 생성
        Review review = ReviewConverter.toCreateReview(dto, user, store);
        reviewRepository.save(review);

        return review.getReviewContent();
    }
}
