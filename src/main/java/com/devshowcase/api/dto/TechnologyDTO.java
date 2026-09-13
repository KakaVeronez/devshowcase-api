package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;

public class TechnologyDTO {

    @NotBlank(message = "O nome da tecnologia é obrigatório")
    private String name;

    public TechnologyDTO() {}

    public TechnologyDTO(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}