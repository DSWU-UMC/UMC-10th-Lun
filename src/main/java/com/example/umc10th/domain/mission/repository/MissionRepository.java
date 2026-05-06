package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 해당 지역 미션 목록
    @Query(value = """
        SELECT s.store_name, 
               s.category,
               DATEDIFF(m.mission_deadline, NOW()) AS remainingDays, 
               m.mission_content, 
               m.accumulated_point
        FROM mission m 
        JOIN store s ON m.store_id = s.store_id 
        WHERE s.region_id = :regionId AND NOT EXISTS (
                 SELECT 1
                 FROM user_mission um
                 WHERE um.user_id = :userId
                   AND um.mission_id = m.mission_id
             )
    """, nativeQuery = true)
    List<MissionResDTO.MissionInfo> findMissionInfoByStoreIds(
            @Param("regionId") Long regionId, @Param("userId") Long userId);

    // 미션 목록 조회
    @Query(value = """
        SELECT s.store_name,
               s.category,
               DATEDIFF(m.mission_deadline, NOW()) AS remainingDays, 
               m.mission_content, 
               m.accumulated_point
        FROM mission m 
        JOIN store s ON m.store_id = s.store_id 
        WHERE m.mission_id IN (
                SELECT mission_id 
                FROM user_mission
                WHERE success = :success AND user_id = :user
            )
    """, nativeQuery = true)
    List<MissionResDTO.MissionInfo> findMissionInfoBySuccess(
            @Param("success") boolean success,
            @Param("user") Long user);
}
