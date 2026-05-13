package com.example.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class ReviewReqDTO {

    // 리뷰 작성
    public record CreateReview(
            @NotNull(message = "유저 아이디는 필수 입니다.")
            Long userId,
            @NotBlank(message = "리뷰 내용은 빈칸일 수 없습니다.")
            String reviewContent,
            @NotNull(message = "별점은 필수 입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0점 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5점 이하여야 합니다.")
            Float rating,
            List<String> photos
    ){}
}
