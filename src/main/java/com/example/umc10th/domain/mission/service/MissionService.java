package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.StoreException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 홈 화면(오프셋 기반)
    public MissionResDTO.PaginationOffset<MissionResDTO.GetHome> getHome(
            MissionReqDTO.GetHome dto,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // regionName 추출
        Region region = regionRepository.findByRegionName(user.getRegion())
                .orElseThrow(() -> new RuntimeException("지역을 찾을 수 없습니다."));
        // 미션 성공 개수
        int successCount = userMissionRepository.countByUserAndSuccess(user,true);

        // 정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else {
            sortInfo = Sort.by("mission.missionId").descending();
        }
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        // 홈 화면 미션 목록 조회
        Page<Mission> missionList = missionRepository.findMissionInfoByStoreIds(region.getRegionId(), user.getUserId(), pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPaginationOffset(
                missionList.map(mission ->
                                MissionConverter.toGetHome(successCount, region.getRegionName(), mission)).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }


    // 미션 목록 조회(오프셋 기반)
    public MissionResDTO.PaginationOffset<MissionResDTO.GetMission> getMission(
            String status,
            MissionReqDTO.GetHome dto,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // 성공 여부
        boolean success = status.equals("completed");

        // 정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else {
            sortInfo = Sort.by("mission.missionId").descending();
        }
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        // 미션 목록 조회(성공 여부)
        Page<Mission> missionList = missionRepository.findUserMissionInfoBySuccess(success, user.getUserId(), pageRequest);

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPaginationOffset(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }


    // 미션 성공
    public Integer getSuccess(Long missionId, MissionReqDTO.GetHome dto) {
        // userId 추출
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return userMissionRepository.updateSuccess(missionId, dto.userId());    // 성공 1, 실패 0 반환
    }


    // 가게 미션 생성
    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toCreateMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);
        return null;
    }

    // 가게 내 미션 조회
    public MissionResDTO.Pagination<MissionResDTO.GetStoreMission> getStoreMission(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ) {
        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        // 커서가 있는 경우
        if(!cursor.equals("-1")){
            // 커서 분리
            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id":
                    // 커서 타입 변환
                    idCursor = Long.parseLong(cursorSplit[1]);
                    // 가게 내 미션들 조회 & where절에 커서값 기입
                    missionList = missionRepository.findMissionsByStore_StoreIdAndMissionIdLessThanOrderByMissionIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        } else {
            // 커서 없이 조회
            missionList = missionRepository.findAllByStore_StoreIdOrderByMissionIdDesc(storeId, pageRequest);
        }
        // 다음 커서 계산
        nextCursor = missionList.getContent().getLast().getMissionId() + ":" + missionList.getContent().getLast().getMissionId();

        // 미션들 응답 DTO로 포장하기
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetStoreMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}
