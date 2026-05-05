package com.quizforge.api.controllers;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.quizforge.api.services.ExamService;
import com.quizforge.api.dtos.CreateExamRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getExamById(@PathVariable Integer id) {
        Map<String, Object> res = examService.getExamById(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllExams() {
        List<Map<String, Object>> res = examService.getAllExams();
        return ResponseEntity.ok(res);
    }
}
