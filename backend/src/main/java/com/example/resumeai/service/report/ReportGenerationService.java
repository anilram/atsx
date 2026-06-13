package com.example.resumeai.service.report;

import org.springframework.stereotype.Service;

@Service
public class ReportGenerationService {

    public byte[] generatePdfReport(Long evaluationId) {
        // TODO: Implement PDF generation using iText
        // This is a placeholder returning empty bytes
        return new byte[0];
    }
}
