package com.example.umc10th.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserReqDTO { // client -> server

    // 마이페이지
    public record GetInfo(
            @NotNull(message = "유저 아이디는 필수 입니다.")
            Long userId
    ){}

    // 회원가입
    public record CreateUser(
            @NotBlank(message = "이름은 빈칸일 수 없습니다.")
            String name,
            @NotBlank(message = "성별은 빈칸일 수 없습니다.")
            String gender,
            @NotBlank(message = "생일은 빈칸일 수 없습니다.")
            String birth,
            @NotBlank(message = "지역은 빈칸일 수 없습니다.")
            String region,
            @NotBlank(message = "주소는 빈칸일 수 없습니다.")
            String address,
            @NotBlank(message = "소셜은 빈칸일 수 없습니다.")
            String socialType
    ){}
}
