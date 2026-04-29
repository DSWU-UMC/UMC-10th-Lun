package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 마이페이지
    @PostMapping("/users/me")  // 나중에 GetMapping으로 수정
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @RequestBody UserReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.getInfo(dto));
    }

    // 회원가입
    @PostMapping("/users")
    public ApiResponse<String> createUser(
            @RequestBody UserReqDTO.CreateUser dto
    ){
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, userService.createUser(dto));
    }



}
