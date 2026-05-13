package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.enums.RegionType;
import java.util.List;

public class MissionConverter {

    // 홈 화면
    public static MissionResDTO.GetHome toGetHome(
            int successCount, RegionType region, Mission mission
    ) {
        return MissionResDTO.GetHome.builder()
                .successCount(successCount)
                .region(region)
                .storeName(mission.getStore().getStoreName())
                .missionDeadline(mission.getMissionDeadline())
                .missionContent(mission.getMissionContent())
                .accumulatedPoint(mission.getAccumulatedPoint())
                .build();
    }

    // 미션 목록 조회
    public static MissionResDTO.GetMission toGetMission(Mission mission){
        return MissionResDTO.GetMission.builder()
                .storeName(mission.getStore().getStoreName())
                .missionDeadline(mission.getMissionDeadline())
                .missionContent(mission.getMissionContent())
                .accumulatedPoint(mission.getAccumulatedPoint())
                .build();
    }

    // 가게 미션 생성
    public static Mission toCreateMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .missionContent(dto.missionContent())
                .accumulatedPoint(dto.accumulatedPoint())
                .missionDeadline(dto.missionDeadline())
                .build();

    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetStoreMission toGetStoreMission(Mission mission) {
        return MissionResDTO.GetStoreMission.builder()
                .missionContent(mission.getMissionContent())
                .accumulatedPoint(mission.getAccumulatedPoint())
                .missionId(mission.getMissionId())
                .build();
    }

    // 페이지네이션 틀 생성(커서 기반)
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }

    // 페이지네이션 틀 생성(오프셋 기반)
    public static <T> MissionResDTO.PaginationOffset<T> toPaginationOffset(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.PaginationOffset.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }



}
