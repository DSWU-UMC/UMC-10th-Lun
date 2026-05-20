package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.entity.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class UserReqDTO { // client -> server

    // 마이페이지
    public record GetInfo(
            @NotNull(message = "유저 아이디는 필수 입니다.")
            Long userId
    ){}

    // 회원가입
    public record CreateUser(
            Terms terms,
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
            List<Food> foodList,
            @NotBlank(message = "이메일는 빈칸일 수 없습니다.")
            String email,
            @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
            String password
    ){}

    // 회원가입 Terms
    public record Terms(
            boolean age,
            boolean service,
            boolean privacy,
            boolean location,
            boolean marketing
    ){}
}
