package com.example.umc10th.domain.mission.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class MissionResDTO {

    // 홈 화면
    @Builder
    public record GetHome(
            Integer successMissionCount,
            String region,
            List<MissionInfo> missions
    ){}

    @Builder
    public record MissionInfo(
            String storeName,
            String foodType,
            Integer remainingDays,
            String missionContent,
            Integer accumulatedPoint
    ){}

    // 미션 목록 조회
    @Builder
    public record GetMission(
            List<MissionInfo> missions
    ){}

}
