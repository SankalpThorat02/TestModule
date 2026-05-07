package com.quizforge.api.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "Cannot be blank!")
    private String username;

    @NotBlank(message = "Cannot be blank!")
    private String password;

    public LoginRequest() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
