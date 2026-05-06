package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id", nullable = false)
    private Long foodId;

    @Column(name = "food_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
}
