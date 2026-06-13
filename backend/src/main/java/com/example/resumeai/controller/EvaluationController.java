package com.example.resumeai.controller;

import com.example.resumeai.dto.request.EvaluationRequest;
import com.example.resumeai.dto.response.EvaluationResultDto;
import com.example.resumeai.service.scoring.MatchScoringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluations")
@RequiredArgsConstructor
@Tag(name = "Evaluation", description = "APIs for resume-JD evaluation")
public class EvaluationController {

    private final MatchScoringService matchScoringService;

    @PostMapping
    @Operation(summary = "Create evaluation", description = "Evaluate a resume against a job description")
    public ResponseEntity<?> createEvaluation(@Valid @RequestBody EvaluationRequest request) {
        EvaluationResultDto result = matchScoringService.evaluate(request.getResumeId(), request.getJobDescriptionId());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get evaluation result", description = "Retrieve evaluation result by ID")
    public ResponseEntity<EvaluationResultDto> getEvaluation(@PathVariable Long id) {
        EvaluationResultDto result = matchScoringService.getEvaluationResult(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/history")
    @Operation(summary = "Get evaluation history", description = "List all evaluations with pagination")
    public ResponseEntity<List<EvaluationResultDto>> getEvaluationHistory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<EvaluationResultDto> results = matchScoringService.getEvaluationHistory(page, size);
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete evaluation", description = "Delete an evaluation by ID")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
