package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    USER_GET_SUCCESS(HttpStatus.OK, "USER200_1", "성공적으로 유저를 조회했습니다."),
    USER_UPDATE_SUCCESS(HttpStatus.OK, "USER200_2", "성공적으로 유저를 수정했습니다."),
    USER_DELETE_SUCCESS(HttpStatus.OK, "USER200_3", "성공적으로 유저를 삭제했습니다."),
    USER_CREATE_SUCCESS(HttpStatus.CREATED, "USER201_1", "성공적으로 유저를 생성했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
