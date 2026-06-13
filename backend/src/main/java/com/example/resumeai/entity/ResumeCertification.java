package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_certifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String issuer;

    @Column(length = 10)
    private String year;

    @Column(name = "credential_id", length = 100)
    private String credentialId;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
