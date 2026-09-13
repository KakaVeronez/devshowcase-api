package com.devshowcase.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

public class ProjectDTO {

    @NotBlank(message = "O título é obrigatório")
    private String title;

    private String description;

    @URL(message = "A URL do repositório deve ser válida")
    private String repositoryUrl;

    @NotNull(message = "O ID do perfil é obrigatório")
    private Long profileId;

    private Set<Long> technologyIds;

    public ProjectDTO() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }

    public Long getProfileId() { return profileId; }
    public void setProfileId(Long profileId) { this.profileId = profileId; }

    public Set<Long> getTechnologyIds() { return technologyIds; }
    public void setTechnologyIds(Set<Long> technologyIds) { this.technologyIds = technologyIds; }
}