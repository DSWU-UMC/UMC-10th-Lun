package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    // 미션 성공 개수
    int countByUserAndSuccess(User userId, boolean b);

    // 미션 성공
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("""
        UPDATE UserMission um
        SET um.success = true
        WHERE um.mission.missionId = :missionId
          AND um.user.userId = :userId
          AND um.success = false
    """)
    int updateSuccess(
            @Param("missionId") Long missionId,
            @Param("userId") Long userId
    );
}
