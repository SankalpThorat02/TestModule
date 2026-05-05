package com.quizforge.api.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateExamRequest {
    @NotBlank(message = "Title cannot be empty!")
    private String title;

    @Min(value = 1, message = "Minimum duration should be One minute")
    @NotNull(message = "Duration cannot be null!")
    private Integer duration;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
}
