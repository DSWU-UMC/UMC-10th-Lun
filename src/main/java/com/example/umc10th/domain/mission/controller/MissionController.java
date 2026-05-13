package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO.GetStoreMission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면(오프셋 기반)
    @PostMapping("/home")  // 나중에 GetMapping으로 수정
    public ApiResponse<MissionResDTO.PaginationOffset<MissionResDTO.GetHome>> home(
            @RequestBody @Valid MissionReqDTO.GetHome dto,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getHome(dto,pageSize, pageNumber, sort));
    }


    // 미션 목록 조회(오프셋 기반 & 진행 중, 진행 완료) status=active/completed
    @PostMapping("/users/me/missions")  // 나중에 @GetMapping으로 수정
    public ApiResponse<MissionResDTO.PaginationOffset<MissionResDTO.GetMission>> mission(
            @RequestParam String status,
            @RequestBody @Valid MissionReqDTO.GetHome dto,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getMission(status, dto, pageSize, pageNumber, sort));
    }


    // 미션 성공
    @PatchMapping("/users/me/missions/{missionId}/success")
    public ApiResponse<Integer> success(
            @PathVariable Long missionId,
            @RequestBody @Valid MissionReqDTO.GetHome dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_UPDATE_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getSuccess(missionId, dto));
    }


    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @RequestBody @Valid MissionReqDTO.CreateMission dto,
            @PathVariable Long storeId
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATE_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }


    // 가게 내 미션들 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<GetStoreMission>> getStoreMission(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getStoreMission(storeId, pageSize, cursor, query));
    }
}
