package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 마이페이지
    @GetMapping("/api/users/me")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthUser user
            ){
        BaseSuccessCode code = UserSuccessCode.USER_GET_SUCCESS;
        return ApiResponse.onSuccess(code, userService.getInfo(user));
    }

    // 회원가입
    @PostMapping("/auth/users")
    public ApiResponse<String> createUser(
            @RequestBody @Valid UserReqDTO.CreateUser dto
    ){
        BaseSuccessCode code = UserSuccessCode.USER_CREATE_SUCCESS;
        return ApiResponse.onSuccess(code, userService.createUser(dto));
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<String> login(
            @RequestBody @Valid UserReqDTO.Login dto
    ){
        BaseSuccessCode code = UserSuccessCode.USER_GET_SUCCESS;
        return ApiResponse.onSuccess(code, userService.login(dto));
    }


}
