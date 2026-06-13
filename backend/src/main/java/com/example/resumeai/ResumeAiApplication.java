package com.example.resumeai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application entry point for Resume AI Evaluation System.
 * 
 * This system evaluates candidate resumes against job descriptions using:
 * - AI-powered document parsing and data extraction
 * - Semantic similarity matching with vector embeddings
 * - Rule-based scoring combined with AI analysis
 * - Comprehensive reporting with visualizations
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class ResumeAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResumeAiApplication.class, args);
    }
}
