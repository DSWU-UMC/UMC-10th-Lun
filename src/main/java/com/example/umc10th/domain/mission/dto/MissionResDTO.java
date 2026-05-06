package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.RegionType;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
public class MissionResDTO {

    // 홈 화면
    @Builder
    public record GetHome(
            Integer successCount,
            RegionType region,
            List<MissionInfo> missions
    ){}

    // 미션 목록 조회
    @Builder
    public record GetMission(
            List<MissionInfo> missions
    ){}

    // mission 리스트
    @Builder
    public record MissionInfo(
            String storeName,
            String category,
            Long remainingDays,
            String missionContent,
            Integer accumulatedPoint
    ){}
}
