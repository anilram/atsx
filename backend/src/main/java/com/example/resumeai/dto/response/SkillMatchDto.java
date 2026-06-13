package com.example.resumeai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillMatchDto {
    
    private String resumeSkillName;
    private String jobSkillName;
    private String matchType;
    private BigDecimal similarityScore;
    private String explanation;
}
