package com.quizforge.api.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.quizforge.api.dtos.AddQuestionRequest;
import com.quizforge.api.services.QuestionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/exams")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/{examId}/questions")
    public ResponseEntity<String> addQuestion(@PathVariable Long examId, @Valid @RequestBody AddQuestionRequest request) {
        String res = questionService.addQuestion(examId, request);
        return ResponseEntity.ok(res);
    }
}
