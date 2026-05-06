package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_GET_SUCCESS(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 조회했습니다."),
    MISSION_UPDATE_SUCCESS(HttpStatus.OK, "MISSION200_2", "성공적으로 미션을 수정했습니다."),
    MISSION_DELETE_SUCCESS(HttpStatus.OK, "MISSION200_3", "성공적으로 미션을 삭제했습니다."),
    MISSION_CREATE_SUCCESS(HttpStatus.CREATED, "MISSION201_1", "성공적으로 미션을 생성했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
