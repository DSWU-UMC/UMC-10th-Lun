package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
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
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getHome(dto));
    }


    // 미션 목록 조회(진행 중, 진행 완료) status=active/completed
    @GetMapping("/users/me/missions")
    public ApiResponse<MissionResDTO.GetMission> mission(
        @RequestParam String status
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMission(status));
    }


    // 미션 성공
    @PatchMapping("/users/me/missions/{missionId}/success")
    public ApiResponse<String> success(
            @PathVariable Long missionId
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getSuccess(missionId));
    }
}
