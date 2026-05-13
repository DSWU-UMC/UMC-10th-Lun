package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404_1", "해당 가게을 찾을 수 없습니다."),
    INVALID_STORE_STATUS(HttpStatus.BAD_REQUEST, "STORE400_2", "가게 상태가 올바르지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
