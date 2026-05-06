package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.umc10th.domain.user.entity.User;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    // 홈 화면
    public MissionResDTO.GetHome getHome(MissionReqDTO.GetHome dto) {
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // regionName 추출
        Region region = regionRepository.findByRegionName(user.getRegion())
                .orElseThrow(() -> new RuntimeException("지역을 찾을 수 없습니다."));
        // 미션 성공 개수
        int successCount = userMissionRepository.countByUserAndSuccess(user,true);
        // 해당 지역 미션 목록
        List<MissionResDTO.MissionInfo> missions = missionRepository.findMissionInfoByStoreIds(region.getRegionId(), user.getUserId());

        return MissionConverter.toGetHome(successCount,region.getRegionName(), missions);
    }


    // 미션 목록 조회
    public MissionResDTO.GetMission getMission(String status, MissionReqDTO.GetHome dto) {
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // 미션 목록 조회
        boolean success = status.equals("completed");
        List<MissionResDTO.MissionInfo> missions = missionRepository.findMissionInfoBySuccess(success, user.getUserId());
        return MissionConverter.toGetMission(missions);
    }


    // 미션 성공
    public Integer getSuccess(Long missionId, MissionReqDTO.GetHome dto) {
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return userMissionRepository.updateSuccess(missionId, dto.userId());    // 성공 1, 실패 0 반환
    }
}
