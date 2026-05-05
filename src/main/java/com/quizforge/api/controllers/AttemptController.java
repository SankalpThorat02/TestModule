package com.quizforge.api.controllers;

import com.quizforge.api.dtos.SaveAnswerRequest;
import com.quizforge.api.services.AttemptService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {
    private final AttemptService attemptService;
    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PutMapping("/{attemptId}/answers")
    public ResponseEntity<Map<String, Object>> saveAnswer(@PathVariable Long attemptId, @Valid  @RequestBody SaveAnswerRequest request) {
        Map<String, Object> res = attemptService.saveAnswer(attemptId, request);
        return ResponseEntity.ok(res);
    }
}
