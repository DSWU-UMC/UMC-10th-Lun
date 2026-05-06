package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.TermType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id", nullable = false)
    private Long termsId;

    @Column(name = "terms_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermType termsName;
}
