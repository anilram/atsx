package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_responsibilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_description_id", nullable = false)
    private JobDescription jobDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
