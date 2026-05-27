package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.security.entity.AuthUser;
import com.example.umc10th.global.security.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    // 마이페이지
    public UserResDTO.GetInfo getInfo(AuthUser user){
        return UserConverter.toGetInfo(user.getUser());
    }

    // 회원가입
    public String createUser(UserReqDTO.CreateUser dto) {
        // 이메일 중복 확인
        if(userRepository.findByEmail(dto.email()).isPresent()) {
            throw new UserException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 비밀번호 BCrypt 솔트처리
        String encodedPwd = passwordEncoder.encode(dto.password());

        // 유저 생성
        User user = UserConverter.toCreateUser(dto, encodedPwd);
        userRepository.save(user);

        return "pwd = " + user.getPassword();
    }

    // 로그인
    public String login(UserReqDTO.Login dto) {
        // 유저 이메일 조회
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UserException(UserErrorCode.EMAIL_NOT_FOUND));
        // 비밀번호 암호화 후 비교
        if(!passwordEncoder.matches(dto.password(), user.getPassword())){
            throw new UserException(UserErrorCode.PASSWORD_NOT_MATCH);
        }
        // 유저 정보 저장
        AuthUser authUser = new AuthUser(user);
        return jwtUtil.createAccessToken(authUser);
    }
}
