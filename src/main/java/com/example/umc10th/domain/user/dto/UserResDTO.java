package com.example.umc10th.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class UserResDTO {  // server -> client

    @Builder
    public record GetInfo(
            String name,
            String email,
            String phone,
            Integer point
    ){}
}
