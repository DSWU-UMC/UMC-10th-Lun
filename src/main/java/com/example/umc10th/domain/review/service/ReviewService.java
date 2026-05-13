package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@Transactional
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

    // 리뷰 조회(커서 기반)
    public ReviewResDTO.Pagination<ReviewResDTO.GetReview> getReview(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ){
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        double ratingCursor;
        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if(!cursor.equals("-1")){
            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id":
                    // 커서 타입 변환
                    idCursor = Long.parseLong(cursorSplit[1]);
                    // 가게 내 미션들 조회 & where절에 커서값 기입
                    reviewList = reviewRepository.findReviewsByStore_StoreIdAndReviewIdLessThanOrderByReviewIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                case "rating":
                    // 커서 타입 변환
                    ratingCursor = Double.parseDouble(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);
                    // 가게 내 미션들 조회 & where절에 커서값 기입
                    reviewList = reviewRepository.findReviewsByRatingCursor(
                            storeId,
                            ratingCursor,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            switch (query.toLowerCase()){
                case "id":
                    reviewList = reviewRepository.findAllByStore_StoreIdOrderByReviewIdDesc(storeId, pageRequest);
                    break;
                case "rating":
                    reviewList = reviewRepository.findAllByStoreOrderByRatingDescReviewIdDesc(storeId, pageRequest);
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        }
        // 다음 커서 계산
        nextCursor = reviewList.getContent().getLast().getRating() + ":" + reviewList.getContent().getLast().getReviewId();

        // 미션들 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toGetReview).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
