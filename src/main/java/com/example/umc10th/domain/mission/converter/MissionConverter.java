package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.RegionType;
import java.util.List;

public class MissionConverter {

    // 홈 화면
    public static MissionResDTO.GetHome toGetHome(int successCount, RegionType region, List<MissionResDTO.MissionInfo> storeList) {
        return MissionResDTO.GetHome.builder()
                .successCount(successCount)
                .region(region)
                .missions(storeList)
                .build();
    }

    // 미션 목록 조회
    public static MissionResDTO.GetMission toGetMission(List<MissionResDTO.MissionInfo> missions) {
        return MissionResDTO.GetMission.builder()
                .missions(missions)
                .build();
    }
}
