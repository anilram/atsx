package com.example.resumeai.repository;

import com.example.resumeai.entity.JobDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
    
    Page<JobDescription> findByUserId(Long userId, Pageable pageable);
    
    List<JobDescription> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<JobDescription> findByStatus(String status);
}
