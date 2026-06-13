package com.example.resumeai.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "resume_embeddings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeEmbedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @Column(name = "embedding_type", nullable = false, length = 50)
    private String embeddingType;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "vector(1536)")
    private float[] embedding;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @Column(name = "created_at")
    private java.time.Instant createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.Instant.now();
    }
}
