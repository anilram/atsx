package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * Skill required or preferred in a job description.
 */
@Entity
@Table(name = "job_skills", indexes = {
    @Index(name = "idx_job_skills_job_id", columnList = "job_description_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_description_id", nullable = false)
    private JobDescription jobDescription;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(name = "is_mandatory")
    @Builder.Default
    private Boolean isMandatory = false;

    @Builder.Default
    private Integer weight = 1;

    @Column(name = "skill_type", length = 50)
    @Builder.Default
    private String skillType = "REQUIRED";

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "vector(1536)")
    private float[] embedding;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
