package com.example.resumeai.repository;

import com.example.resumeai.entity.ResumeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeSkillRepository extends JpaRepository<ResumeSkill, Long> {
    
    List<ResumeSkill> findByResumeId(Long resumeId);
    
    List<ResumeSkill> findByResumeIdAndCategory(Long resumeId, ResumeSkill.SkillCategory category);
    
    @Query(value = "SELECT * FROM resume_skills WHERE resume_id = :resumeId ORDER BY vector_cosine_similarity(embedding, :embedding) DESC LIMIT :limit", 
           nativeQuery = true)
    List<ResumeSkill> findSimilarSkills(@Param("resumeId") Long resumeId, 
                                        @Param("embedding") float[] embedding, 
                                        @Param("limit") int limit);
}
