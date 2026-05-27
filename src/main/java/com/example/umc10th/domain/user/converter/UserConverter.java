package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.mission.enums.RegionType;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.global.security.dto.OAuthDTO;

import java.time.LocalDate;

public class UserConverter {

    // 마이페이지
    public static UserResDTO.GetInfo toGetInfo(
            User user
    ) {
        return UserResDTO.GetInfo.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .point(user.getPoint())
                .build();
    }

    // 회원가입
    public static User toCreateUser(
            UserReqDTO.CreateUser dto,
            String encodedPwd
    ) {
        return User.builder()
                .name(dto.name())
                .gender(Gender.valueOf(dto.gender()))
                .birth(LocalDate.parse(dto.birth()))
                .region(RegionType.valueOf(dto.region()))
                .address(dto.address())
                .email(dto.email())
                .password(encodedPwd)
                .build();
    }

    // 소셜 로그인 정보
    public static User toUser(OAuthDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }

    // 토큰 정보
    public static UserResDTO.Login toLogin(String accessToken) {
        return UserResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
