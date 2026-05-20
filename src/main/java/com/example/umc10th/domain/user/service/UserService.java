package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 마이페이지
    public UserResDTO.GetInfo getInfo(UserReqDTO.GetInfo dto){
        // DTO에서 유저 ID 추출
        Long userId = dto.userId();
        // DB에서 해당 유저 ID로 데이터 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // 컨버터를 이용해서 응답 DTO 생성 & return
        return UserConverter.toGetInfo(user);
    }

    // 회원가입
    public String createUser(UserReqDTO.CreateUser dto) {
        // 비밀번호 BCrypt 솔트처리
        String encodedPwd = passwordEncoder.encode(dto.password());

        // 유저 생성
        User user = UserConverter.toCreateUser(dto, encodedPwd);
        userRepository.save(user);

        return "pwd = " + user.getPassword();
    }
}
