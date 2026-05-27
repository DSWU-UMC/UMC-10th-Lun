package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 유저 이메일 조회
    Optional<User> findByEmail(String username);

    // 소셜 로그인 정보
    Optional<User> findBySocialTypeAndSocialUid(SocialType providerId, String socialUid);
}
