package com.quizforge.api.services;

import com.quizforge.api.dtos.SaveAnswerRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Parameter;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AttemptService {
    private final EntityManager entityManager;
    public AttemptService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Map<String, Object> saveAnswer(Long attemptId, SaveAnswerRequest answerRequest) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("save_candidate_answer");

        query.registerStoredProcedureParameter("in_attempt_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_question_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("in_selected_option", String.class,ParameterMode.IN);
        query.registerStoredProcedureParameter("out_message", String.class, ParameterMode.OUT);

        query.setParameter("in_attempt_id", attemptId);
        query.setParameter("in_question_id", answerRequest.getQuestionId());
        query.setParameter("in_selected_option", answerRequest.getSelectedOption());

        query.execute();

        String message = (String) query.getOutputParameterValue("out_message");
        Map<String, Object> res = new HashMap<>();
        res.put("message", message);

        return res;
    }
}
