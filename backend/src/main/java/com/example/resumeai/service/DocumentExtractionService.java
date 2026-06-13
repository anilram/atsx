package com.example.resumeai.service;

import com.example.resumeai.dto.response.ResumeUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DocumentExtractionService {

    public ResumeUploadResponse processResumeFile(MultipartFile file) {
        // TODO: Implement file parsing, AI extraction, and storage
        return ResumeUploadResponse.builder()
                .resumeId(1L)
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .contentType(file.getContentType())
                .status("PARSED")
                .uploadedAt(java.time.Instant.now())
                .build();
    }

    public ResumeUploadResponse processResumeText(String text) {
        // TODO: Implement AI extraction from text
        return ResumeUploadResponse.builder()
                .resumeId(1L)
                .fileName("text_input.txt")
                .status("PARSED")
                .uploadedAt(java.time.Instant.now())
                .build();
    }

    public Object processJobDescriptionFile(MultipartFile file, String title) {
        // TODO: Implement JD file processing
        return new Object();
    }

    public Object processJobDescriptionText(String title, String text) {
        // TODO: Implement JD text processing
        return new Object();
    }
}
