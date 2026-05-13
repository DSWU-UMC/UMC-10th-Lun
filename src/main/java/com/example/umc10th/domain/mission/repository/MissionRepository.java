package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 홈 화면 미션 목록
    @Query("""
        SELECT m
        FROM Mission m
        JOIN m.store s
        WHERE s.region.regionId = :regionId AND NOT EXISTS (
            SELECT um
            FROM UserMission um
            WHERE um.user.userId = :userId
            AND um.mission.missionId = m.missionId)
    """)
    Page<Mission> findMissionInfoByStoreIds(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId,
            Pageable pageable);


    // 미션 목록 조회(성공 여부)
    @Query("""
        SELECT um.mission
        FROM UserMission um
        WHERE um.success = :success
          AND um.user.userId = :userId
    """)
    Page<Mission> findUserMissionInfoBySuccess(
            @Param("success") boolean success,
            @Param("userId") Long userId,
            Pageable pageable
    );


    // 가게 내 미션 조회(커서O)
    Slice<Mission> findMissionsByStore_StoreIdAndMissionIdLessThanOrderByMissionIdDesc(Long storeId, long idCursor, PageRequest pageRequest);

    // 가게 내 미션 조회(커서X)
    Slice<Mission> findAllByStore_StoreIdOrderByMissionIdDesc(Long storeId, PageRequest pageRequest);
}
