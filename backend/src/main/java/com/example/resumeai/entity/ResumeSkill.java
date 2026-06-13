package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Skill extracted from a resume.
 */
@Entity
@Table(name = "resume_skills", indexes = {
    @Index(name = "idx_resume_skills_resume_id", columnList = "resume_id"),
    @Index(name = "idx_resume_skills_category", columnList = "category")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Builder.Default
    private SkillCategory category = SkillCategory.OTHER;

    @Column(name = "years_experience", precision = 4, scale = 2)
    private Double yearsExperience;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @Builder.Default
    private SkillLevel level = SkillLevel.INTERMEDIATE;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "vector(1536)")
    private float[] embedding;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }

    public enum SkillCategory {
        BACKEND, FRONTEND, CLOUD, DEVOPS, DATABASE, TESTING, AI, OTHER
    }

    public enum SkillLevel {
        BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    }
}
