package com.example.resumeai.controller;

import com.example.resumeai.dto.request.JobDescriptionRequest;
import com.example.resumeai.service.DocumentExtractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
@Tag(name = "Job Description Management", description = "APIs for job description management")
public class JobDescriptionController {

    private final DocumentExtractionService documentExtractionService;

    @PostMapping("/upload")
    @Operation(summary = "Upload JD file", description = "Upload PDF, DOC, or DOCX job description file")
    public ResponseEntity<?> uploadJobDescription(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "title", required = false) String title) {
        var response = documentExtractionService.processJobDescriptionFile(file, title);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/text")
    @Operation(summary = "Create JD from text", description = "Create job description from plain text")
    public ResponseEntity<?> createJobDescriptionFromText(
            @Valid @RequestBody JobDescriptionRequest request) {
        var response = documentExtractionService.processJobDescriptionText(request.getTitle(), request.getText());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get JD by ID", description = "Retrieve job description details")
    public ResponseEntity<?> getJobDescription(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete JD", description = "Delete a job description by ID")
    public ResponseEntity<Void> deleteJobDescription(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
