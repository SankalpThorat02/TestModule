package com.quizforge.api.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.scheduling.support.SimpleTriggerContext;

public class AddQuestionRequest {
    @NotBlank(message = "Cannot be blank!")
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @NotBlank(message = "Cannot be blank!")
    private String correctOption;

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() {return optionB;}
    public void setOptionB(String optionB) {this.optionB = optionB;}

    public String getOptionC() {return optionC;}
    public void setOptionC(String optionC) {this.optionC = optionC;}

    public String getOptionD() {return optionD;}
    public void setOptionD(String optionD) {this.optionD = optionD;}

    public String getCorrectOption() {return correctOption;}
    public void setCorrectOption(String correctOption) {this.correctOption = correctOption;}
}
