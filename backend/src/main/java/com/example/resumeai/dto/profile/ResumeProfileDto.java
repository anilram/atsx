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
public class ResumeProfileDto {
    
    private String candidateName;
    private String email;
    private String phone;
    private Double totalExperienceYears;
    private String currentRole;
    private String summary;
    private List<SkillDto> skills;
    private List<DegreeDto> degrees;
    private List<CertificationDto> certifications;
    private List<AchievementDto> achievements;
    private List<ProjectDto> projects;
}
