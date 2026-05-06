package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="user_mission")
public class UserMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id", nullable = false)
    private Long userMissionId;

    @Builder.Default
    private boolean success = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
