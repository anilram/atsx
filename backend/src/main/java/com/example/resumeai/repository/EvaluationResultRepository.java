package com.example.resumeai.repository;

import com.example.resumeai.entity.EvaluationResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationResultRepository extends JpaRepository<EvaluationResult, Long> {
    
    Page<EvaluationResult> findByResumeId(Long resumeId, Pageable pageable);
    
    Page<EvaluationResult> findByJobDescriptionId(Long jobDescriptionId, Pageable pageable);
    
    List<EvaluationResult> findByResumeIdAndJobDescriptionId(Long resumeId, Long jobDescriptionId);
    
    Optional<EvaluationResult> findByIdAndStatus(Long id, EvaluationResult.EvaluationStatus status);
    
    @Query("SELECT e FROM EvaluationResult e WHERE e.status = 'COMPLETED' ORDER BY e.evaluatedAt DESC")
    Page<EvaluationResult> findCompletedEvaluations(Pageable pageable);
}
