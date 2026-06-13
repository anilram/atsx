package com.example.resumeai.controller;

import com.example.resumeai.dto.request.ResumeUploadRequest;
import com.example.resumeai.dto.response.ResumeUploadResponse;
import com.example.resumeai.service.DocumentExtractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
@Tag(name = "Resume Management", description = "APIs for resume upload and management")
public class ResumeController {

    private final DocumentExtractionService documentExtractionService;

    @PostMapping("/upload")
    @Operation(summary = "Upload resume file", description = "Upload PDF, DOC, or DOCX resume file")
    public ResponseEntity<ResumeUploadResponse> uploadResume(
            @RequestParam("file") MultipartFile file) {
        ResumeUploadResponse response = documentExtractionService.processResumeFile(file);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/text")
    @Operation(summary = "Submit resume as text", description = "Submit resume content as plain text")
    public ResponseEntity<ResumeUploadResponse> submitResumeText(
            @Valid @RequestBody ResumeUploadRequest request) {
        ResumeUploadResponse response = documentExtractionService.processResumeText(request.getText());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get resume by ID", description = "Retrieve detailed resume information")
    public ResponseEntity<?> getResume(@PathVariable Long id) {
        // Implementation pending
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete resume", description = "Delete a resume by ID")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        // Implementation pending
        return ResponseEntity.noContent().build();
    }
}
