package com.example.resumeai.controller;

import com.example.resumeai.service.report.ReportGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
@Tag(name = "Report Generation", description = "APIs for PDF report generation")
public class ReportController {

    private final ReportGenerationService reportGenerationService;

    @GetMapping("/{evaluationId}/download")
    @Operation(summary = "Download PDF report", description = "Download evaluation report as PDF")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Long evaluationId) {
        byte[] pdfContent = reportGenerationService.generatePdfReport(evaluationId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "evaluation_report_" + evaluationId + ".pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfContent);
    }
}
