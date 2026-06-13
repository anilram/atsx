package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Evaluation result storing the match analysis between resume and job description.
 */
@Entity
@Table(name = "evaluation_results", indexes = {
    @Index(name = "idx_evaluation_results_resume_id", columnList = "resume_id"),
    @Index(name = "idx_evaluation_results_job_id", columnList = "job_description_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @Column(name = "job_description_id", nullable = false)
    private Long jobDescriptionId;

    @Column(name = "overall_match_percentage", precision = 5, scale = 2)
    private BigDecimal overallMatchPercentage;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Recommendation recommendation;

    @Column(name = "high_level_summary", columnDefinition = "TEXT")
    private String highLevelSummary;

    @Column(name = "technical_skill_score", precision = 5, scale = 2)
    private BigDecimal technicalSkillScore;

    @Column(name = "experience_score", precision = 5, scale = 2)
    private BigDecimal experienceScore;

    @Column(name = "domain_score", precision = 5, scale = 2)
    private BigDecimal domainScore;

    @Column(name = "education_score", precision = 5, scale = 2)
    private BigDecimal educationScore;

    @Column(name = "certification_score", precision = 5, scale = 2)
    private BigDecimal certificationScore;

    @Column(name = "achievement_score", precision = 5, scale = 2)
    private BigDecimal achievementScore;

    @Column(name = "soft_skill_score", precision = 5, scale = 2)
    private BigDecimal softSkillScore;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private String[] strengths;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "text[]")
    private String[] improvements;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Builder.Default
    private EvaluationStatus status = EvaluationStatus.PENDING;

    @Column(name = "evaluated_at")
    private java.time.Instant evaluatedAt;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @Column(name = "updated_at")
    private java.time.Instant updatedAt;

    @OneToMany(mappedBy = "evaluationResult", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SkillMatchResult> skillMatchResults = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
        updatedAt = java.time.Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.Instant.now();
    }

    public enum Recommendation {
        STRONG_MATCH, MODERATE_MATCH, WEAK_MATCH, REJECT
    }

    public enum EvaluationStatus {
        PENDING, PROCESSING, COMPLETED, FAILED
    }
}
