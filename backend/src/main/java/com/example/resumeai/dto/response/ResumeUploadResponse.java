package com.example.resumeai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeUploadResponse {
    
    private Long resumeId;
    private String fileName;
    private Long fileSize;
    private String contentType;
    private String status;
    private String candidateName;
    private Instant uploadedAt;
}
