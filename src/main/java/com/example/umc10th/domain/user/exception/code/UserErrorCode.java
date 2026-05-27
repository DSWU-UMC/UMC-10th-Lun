package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER404_1", "해당 유저를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "USER409_1", "이미 존재하는 유저입니다."),
    INVALID_USER_STATUS(HttpStatus.BAD_REQUEST, "USER400_1", "유저 상태가 올바르지 않습니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "EMAIL404_1", "해당 이메일을 찾을 수 없습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.NOT_FOUND, "PASSWORD404_1", "해당 비밀번호를 찾을 수 없습니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "SOCIAL400_1", "지원하지 않는 소셜 로그인 제공자입니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "EMAIL409_1", "이미 존재하는 이메일입니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
