package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.RegionType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class MissionResDTO {

    // 홈 화면
    @Builder
    public record GetHome(
            Integer successCount,
            RegionType region,
            String storeName,
            LocalDate missionDeadline,
            String missionContent,
            Integer accumulatedPoint
    ){}

    // 미션 목록 조회
    @Builder
    public record GetMission(
            String storeName,
            LocalDate missionDeadline,
            String missionContent,
            Integer accumulatedPoint
    ){}

    // 가게 내 미션 조회
    @Builder
    public record GetStoreMission (
            Long missionId,
            Integer accumulatedPoint,
            String missionContent
    ){}

    // 페이지네이션 틀(오프셋 기반)
    @Builder
    public record PaginationOffset<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

    // 페이지네이션 틀(커서 기반)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
