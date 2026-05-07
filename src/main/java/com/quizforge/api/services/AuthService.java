package com.quizforge.api.services;

import com.quizforge.api.controllers.AuthController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;


@Service
public class AuthService {
    private EntityManager entityManager;

    public AuthService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Map<String, String> login(String username, String password) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("login_users");

        query.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_password", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("out_role", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("out_name", String.class, ParameterMode.OUT);

        query.setParameter("p_username", username);
        query.setParameter("p_password", password);

        query.execute();

        String role = (String) query.getOutputParameterValue("out_role");
        String name = (String) query.getOutputParameterValue("out_name");

        if("INVALID".equals(role)) return null;

        return Map.of("role", role, "name", name);
    }
}
