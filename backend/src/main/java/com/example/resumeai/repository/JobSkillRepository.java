package com.example.resumeai.repository;

import com.example.resumeai.entity.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    
    List<JobSkill> findByJobDescriptionId(Long jobDescriptionId);
    
    List<JobSkill> findByJobDescriptionIdAndIsMandatory(Long jobDescriptionId, Boolean isMandatory);
    
    List<JobSkill> findByJobDescriptionIdAndSkillType(Long jobDescriptionId, String skillType);
}
