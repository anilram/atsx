package com.example.resumeai.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillRequirementDto {
    
    private String name;
    private Boolean mandatory;
    private Integer weight;
}
