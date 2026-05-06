package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.enums.RegionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionName(RegionType regionName);
}
