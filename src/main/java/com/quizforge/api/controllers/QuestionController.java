package com.quizforge.api.controllers;

import com.quizforge.api.dtos.ExcelHelper;
import com.quizforge.api.dtos.QuestionUpload;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.quizforge.api.dtos.AddQuestionRequest;
import com.quizforge.api.services.QuestionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/{examId}/questions/upload")
    public ResponseEntity<?> uploadQuestion(@PathVariable Long examId, @RequestParam("file")MultipartFile file) {
        try {
            List<QuestionUpload> parsedQuestions = ExcelHelper.excelToQuestions(file.getInputStream());
            String resultMessage = questionService.uploadQuestions(examId, parsedQuestions);

            return ResponseEntity.ok(Map.of("message", resultMessage));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "UPLOAD FAILED: " + e.getMessage()));
        }
    }
}
