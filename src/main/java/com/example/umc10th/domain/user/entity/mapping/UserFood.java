package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user_food")
public class UserFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_food_id", nullable = false)
    private Long userFoodId;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
