package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "해당 가게를 찾을 수 없습니다."),
    REVIEW_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401_1", "로그인하지 않았습니다."),
    REVIEW_BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "이미 작성된 가게 리뷰입니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
