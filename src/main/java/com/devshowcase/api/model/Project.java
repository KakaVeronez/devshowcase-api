package com.devshowcase.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @URL
    private String repositoryUrl;

    private Long upvotes = 0L;

    private Double averageRating = 0.0;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToMany
    @JoinTable(
        name = "tb_project_technology",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technology> technologies = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    public Project() {}

    public Project(Long id, String title, String description, String repositoryUrl, Profile profile) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.repositoryUrl = repositoryUrl;
        this.profile = profile;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }

    public Long getUpvotes() { return upvotes; }
    public void setUpvotes(Long upvotes) { this.upvotes = upvotes; }

    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public Set<Technology> getTechnologies() { return technologies; }
    public void setTechnologies(Set<Technology> technologies) { this.technologies = technologies; }

    public List<Feedback> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<Feedback> feedbacks) { this.feedbacks = feedbacks; }
}