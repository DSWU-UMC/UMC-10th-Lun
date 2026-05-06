package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.RegionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="region")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="region_id", nullable = false)
    private Long regionId;

    @Column(name = "region_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegionType regionName;
}
