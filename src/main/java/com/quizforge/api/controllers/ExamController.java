package com.quizforge.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.quizforge.api.services.ExamService;
import com.quizforge.api.dtos.CreateExamRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createExam(@RequestBody CreateExamRequest requestDto) {
        Map<String, Object> response = examService.createExam(requestDto);
        return ResponseEntity.ok(response);
    }
}
