package com.example.umc10th.domain.user.dto;

import lombok.Data;

@Data
public class UserReqDTO { // client -> server

    // 마이페이지
    public record GetInfo(
            Long userId
    ){}

    // 회원가입
    public record CreateUser(
            String name,
            String gender,
            String birth,
            String region,
            String address,
            String socialType
    ){}
}
