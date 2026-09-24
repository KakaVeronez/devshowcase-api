package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FeedbackDTO {

    @NotBlank(message = "O comentário não pode ficar em branco")
    private String comment;

    @NotNull(message = "A avaliação é obrigatória")
    private Integer rating;

    public FeedbackDTO() {}

    public FeedbackDTO(String comment, Integer rating) {
        this.comment = comment;
        this.rating = rating;
    }

    public String getComment() { 
        return comment; 
    }
    
    public void setComment(String comment) { 
        this.comment = comment; 
    }

    public Integer getRating() { 
        return rating; 
    }
    
    public void setRating(Integer rating) { 
        this.rating = rating; 
    }
}