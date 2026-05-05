package com.quizforge.api.services;

import com.quizforge.api.dtos.CreateExamRequest;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamService {
    private EntityManager entityManager;

    public ExamService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Map<String, Object> createExam(CreateExamRequest request) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("create_new_exam");

        query.registerStoredProcedureParameter("in_title", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_duration", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("out_exam_id", Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("out_message", String.class, ParameterMode.OUT);

        query.setParameter("in_title", request.getTitle());
        query.setParameter("in_duration", request.getDuration());

        query.execute();

        Map<String, Object> response = new HashMap<>();
        Object dbExamId = query.getOutputParameterValue("out_exam_id");

        if(dbExamId != null) {
            response.put("examId", ((Number) dbExamId).longValue());
        }

        response.put("message", query.getOutputParameterValue("out_message"));

        return response;
    }
}
