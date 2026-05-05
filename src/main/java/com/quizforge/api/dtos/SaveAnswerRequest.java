package com.quizforge.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SaveAnswerRequest {

    @NotNull(message = "Cannot be empty!")
    private Integer questionId;

    @NotBlank(message = "Cannot be empty!")
    private String selectedOption;

    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }
}
