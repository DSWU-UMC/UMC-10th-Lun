package com.example.umc10th.domain.user.entity.mapping;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="user_term")
public class UserTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_terms_id", nullable = false)
    private Long userTermsId;

    @ManyToOne
    @JoinColumn(name = "terms_id", nullable = false)
    private Term terms;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
