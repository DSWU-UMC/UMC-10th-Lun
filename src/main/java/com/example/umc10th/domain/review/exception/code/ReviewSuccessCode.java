package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode{

    REVIEW_GET_SUCCESS(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 조회했습니다."),
    REVIEW_UPDATE_SUCCESS(HttpStatus.OK, "REVIEW200_2", "성공적으로 리뷰를 수정했습니다."),
    REVIEW_DELETE_SUCCESS(HttpStatus.OK, "REVIEW200_3", "성공적으로 리뷰를 삭제했습니다."),
    REVIEW_CREATE_SUCCESS(HttpStatus.CREATED, "REVIEW201_1", "성공적으로 리뷰를 생성했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
