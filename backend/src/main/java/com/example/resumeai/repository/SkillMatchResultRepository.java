package com.example.resumeai.repository;

import com.example.resumeai.entity.SkillMatchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillMatchResultRepository extends JpaRepository<SkillMatchResult, Long> {
    
    List<SkillMatchResult> findByEvaluationResultId(Long evaluationResultId);
    
    List<SkillMatchResult> findByEvaluationResultIdAndMatchType(Long evaluationResultId, SkillMatchResult.MatchType matchType);
}
