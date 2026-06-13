package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_soft_skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSoftSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_description_id", nullable = false)
    private JobDescription jobDescription;

    @Column(nullable = false, length = 255)
    private String skill;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
