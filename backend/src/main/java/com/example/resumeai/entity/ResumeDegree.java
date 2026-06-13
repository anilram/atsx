package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_degrees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDegree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Column(nullable = false, length = 255)
    private String degree;

    @Column(length = 255)
    private String university;

    @Column(name = "graduation_year", length = 10)
    private String graduationYear;

    @Column(name = "field_of_study", length = 255)
    private String fieldOfStudy;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
