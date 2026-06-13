package com.example.resumeai.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDescriptionRequest {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String text;
}
