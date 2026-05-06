package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 홈 화면
    @PostMapping("/home")  // 나중에 GetMapping으로 수정
    public ApiResponse<MissionResDTO.GetHome> home(
            @RequestBody MissionReqDTO.GetHome dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }


    // 미션 목록 조회(진행 중, 진행 완료) status=active/completed
    @PostMapping("/users/me/missions")  // 나중에 @GetMapping으로 수정
    public ApiResponse<MissionResDTO.GetMission> mission(
            @RequestParam String status,
            @RequestBody MissionReqDTO.GetHome dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_GET_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getMission(status, dto));
    }


    // 미션 성공
    @PatchMapping("/users/me/missions/{missionId}/success")
    public ApiResponse<Integer> success(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.GetHome dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_UPDATE_SUCCESS;
        return ApiResponse.onSuccess(code, missionService.getSuccess(missionId, dto));
    }
}
