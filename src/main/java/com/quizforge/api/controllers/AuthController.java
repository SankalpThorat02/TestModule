package com.quizforge.api.controllers;

import com.quizforge.api.dtos.LoginRequest;
import com.quizforge.api.services.AuthService;
import com.quizforge.api.security.JwtUtil; // IMPORTANT: Import your new utility!
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil; // 1. Bring in the wristband factory

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest requestDTO) {

        // Check the database
        Map<String, String> userDetails = authService.login(requestDTO.getUsername(), requestDTO.getPassword());

        // If INVALID, kick them out
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }

        // 2. THE MAGIC: Print the VIP Wristband!
        String token = jwtUtil.generateToken(requestDTO.getUsername(), userDetails.get("role"));

        // 3. Send the token back to the frontend along with their name
        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", userDetails.get("role"),
                "name", userDetails.get("name")
        ));
    }
}