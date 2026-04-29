package com.example.umc10th.domain.mission.dto;

import lombok.Data;

@Data
public class MissionReqDTO {

    public record GetHome(
            Long userId
    ){}
}
