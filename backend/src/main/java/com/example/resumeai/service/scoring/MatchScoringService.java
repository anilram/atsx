package com.example.resumeai.service.scoring;

import com.example.resumeai.dto.response.EvaluationResultDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class MatchScoringService {

    public EvaluationResultDto evaluate(Long resumeId, Long jobDescriptionId) {
        // TODO: Implement full evaluation logic
        return EvaluationResultDto.builder()
                .id(1L)
                .resumeId(resumeId)
                .jobDescriptionId(jobDescriptionId)
                .overallMatchPercentage(BigDecimal.valueOf(82.5))
                .recommendation("Strong Match")
                .highLevelSummary("Candidate demonstrates strong alignment with job requirements.")
                .technicalSkillScore(BigDecimal.valueOf(85.0))
                .experienceScore(BigDecimal.valueOf(80.0))
                .domainScore(BigDecimal.valueOf(75.0))
                .educationScore(BigDecimal.valueOf(90.0))
                .certificationScore(BigDecimal.valueOf(60.0))
                .achievementScore(BigDecimal.valueOf(78.0))
                .softSkillScore(BigDecimal.valueOf(70.0))
                .matchedSkills(List.of())
                .missingSkills(List.of())
                .partialMatchedSkills(List.of())
                .skillDistributionPieChart(List.of())
                .skillTimelineBarChart(List.of())
                .degrees(List.of())
                .certifications(List.of())
                .achievements(List.of())
                .strengths(List.of("Strong technical skills", "Relevant experience"))
                .improvements(List.of("Gain more cloud experience"))
                .status("COMPLETED")
                .evaluatedAt(Instant.now())
                .build();
    }

    public EvaluationResultDto getEvaluationResult(Long id) {
        // TODO: Fetch from database
        return evaluate(1L, 1L);
    }

    public List<EvaluationResultDto> getEvaluationHistory(int page, int size) {
        // TODO: Fetch paginated history from database
        return List.of(getEvaluationResult(1L));
    }
}
