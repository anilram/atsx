package com.example.resumeai.repository;

import com.example.resumeai.entity.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    
    Page<Resume> findByUserId(Long userId, Pageable pageable);
    
    List<Resume> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    Optional<Resume> findByCandidateName(String candidateName);
    
    @Query("SELECT r FROM Resume r WHERE r.status = :status")
    List<Resume> findByStatus(String status);
}
