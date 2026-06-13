package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Individual skill match result between resume skill and job skill.
 */
@Entity
@Table(name = "skill_match_results", indexes = {
    @Index(name = "idx_skill_match_results_evaluation_id", columnList = "evaluation_result_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillMatchResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_result_id", nullable = false)
    private EvaluationResult evaluationResult;

    @Column(name = "resume_skill_id")
    private Long resumeSkillId;

    @Column(name = "job_skill_id")
    private Long jobSkillId;

    @Enumerated(EnumType.STRING)
    @Column(name = "match_type", nullable = false, length = 50)
    private MatchType matchType;

    @Column(name = "similarity_score", precision = 5, scale = 4)
    private BigDecimal similarityScore;

    @Column(name = "resume_skill_name", nullable = false, length = 255)
    private String resumeSkillName;

    @Column(name = "job_skill_name", nullable = false, length = 255)
    private String jobSkillName;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }

    public enum MatchType {
        EXACT, PARTIAL, SEMANTIC, MISSING
    }
}
