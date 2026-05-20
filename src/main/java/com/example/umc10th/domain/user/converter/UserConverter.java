package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.mission.enums.RegionType;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.Gender;
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
}
