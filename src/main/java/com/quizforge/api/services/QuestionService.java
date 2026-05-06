package com.quizforge.api.services;

import com.quizforge.api.dtos.QuestionUpload;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.quizforge.api.dtos.AddQuestionRequest;

import java.util.List;


@Service
public class QuestionService {
    private final EntityManager entityManager;

    public QuestionService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public String addQuestion(Long examId, AddQuestionRequest request) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_question_to_exam");

        query.registerStoredProcedureParameter("in_exam_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_question_text", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_opt_a", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_opt_b", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_opt_c", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_opt_d", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_correct_opt", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("out_message", String.class, ParameterMode.OUT);

        query.setParameter("in_exam_id", examId);
        query.setParameter("in_question_text", request.getQuestionText());
        query.setParameter("in_opt_a", request.getOptionA());
        query.setParameter("in_opt_b", request.getOptionB());
        query.setParameter("in_opt_c", request.getOptionC());
        query.setParameter("in_opt_d", request.getOptionD());
        query.setParameter("in_correct_opt", request.getCorrectOption());

        query.execute();

        return (String) query.getOutputParameterValue("out_message");
    }

    @Transactional
    public String uploadQuestions(Long examId, List<QuestionUpload> questions) {
        for(QuestionUpload q : questions) {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_question_to_exam");

            query.registerStoredProcedureParameter("in_exam_id", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_question_text", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_opt_a", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_opt_b", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_opt_c", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_opt_d", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("in_correct_opt", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("out_message", String.class, ParameterMode.OUT);

            query.setParameter("in_exam_id", examId);
            query.setParameter("in_question_text", q.getQuestionText());
            query.setParameter("in_opt_a", q.getOptionA());
            query.setParameter("in_opt_b", q.getOptionB());
            query.setParameter("in_opt_c", q.getOptionC());
            query.setParameter("in_opt_d", q.getOptionD());
            query.setParameter("in_correct_opt", q.getCorrectOption());

            query.execute();
        }

        return "SUCCESS: All " + questions.size() + " questions were uploaded safely.";
    }
}
