package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MissionReqDTO {

    // 유저 아이디
    public record GetHome(
            @NotNull(message = "사용자 아이디는 필수입니다.")
            Long userId
    ){}

    // 가게 미션 생성
    public record CreateMission (
            @NotNull(message = "마감기한은 필수입니다.")
            @Future(message = "마감일은 미래 날짜여야 합니다.")
            LocalDate missionDeadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            @Min(value = 1, message = "미션 성공 포인트는 1 이상이여야 합니다. ")
            Integer accumulatedPoint,
            @NotNull(message = "조건은 빈칸일 수 없습니다.")
            String missionContent
    ){}
}
