package com.example.resumeai.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationResultDto {
    
    private Long id;
    private Long resumeId;
    private Long jobDescriptionId;
    private BigDecimal overallMatchPercentage;
    private String recommendation;
    private String highLevelSummary;
    private BigDecimal technicalSkillScore;
    private BigDecimal experienceScore;
    private BigDecimal domainScore;
    private BigDecimal educationScore;
    private BigDecimal certificationScore;
    private BigDecimal achievementScore;
    private BigDecimal softSkillScore;
    private List<SkillMatchDto> matchedSkills;
    private List<SkillMatchDto> missingSkills;
    private List<SkillMatchDto> partialMatchedSkills;
    private List<PieChartDataDto> skillDistributionPieChart;
    private List<BarChartDataDto> skillTimelineBarChart;
    private List<DegreeDto> degrees;
    private List<CertificationDto> certifications;
    private List<AchievementDto> achievements;
    private List<String> strengths;
    private List<String> improvements;
    private String status;
    private Instant evaluatedAt;
}
