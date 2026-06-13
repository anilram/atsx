package com.example.resumeai.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDescriptionProfileDto {
    
    private String jobTitle;
    private Double requiredExperienceYears;
    private List<SkillRequirementDto> requiredSkills;
    private List<SkillRequirementDto> preferredSkills;
    private String domain;
    private List<String> educationRequirements;
    private List<String> certificationRequirements;
    private List<String> responsibilities;
    private List<String> softSkills;
}
