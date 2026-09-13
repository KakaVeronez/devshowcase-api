package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;

public class FeedbackDTO {

    @NotBlank(message = "O comentário não pode ficar em branco")
    private String comment;

    public FeedbackDTO() {}

    public FeedbackDTO(String comment) {
        this.comment = comment;
    }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}