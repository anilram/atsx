package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_certification_requirements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCertificationRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_description_id", nullable = false)
    private JobDescription jobDescription;

    @Column(nullable = false, length = 255)
    private String requirement;

    @Column(name = "is_mandatory")
    @Builder.Default
    private Boolean isMandatory = false;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
