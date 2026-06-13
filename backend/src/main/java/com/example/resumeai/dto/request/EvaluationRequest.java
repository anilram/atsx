package com.example.resumeai.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRequest {
    
    @NotNull(message = "Resume ID is required")
    private Long resumeId;
    
    @NotNull(message = "Job Description ID is required")
    private Long jobDescriptionId;
}
