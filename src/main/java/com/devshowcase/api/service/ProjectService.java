package com.devshowcase.api.service;

import com.devshowcase.api.dto.FeedbackDTO;
import com.devshowcase.api.exception.ResourceNotFoundException;
import com.devshowcase.api.model.Feedback;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.repository.FeedbackRepository;
import com.devshowcase.api.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FeedbackRepository feedbackRepository;

    public ProjectService(ProjectRepository projectRepository, FeedbackRepository feedbackRepository) {
        this.projectRepository = projectRepository;
        this.feedbackRepository = feedbackRepository;
    }

    // POST /api/projects/{id}/feedbacks
    @Transactional
    public Feedback addFeedback(Long projectId, FeedbackDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com ID: " + projectId));

        Feedback feedback = new Feedback();
        feedback.setRating(dto.rating());
        feedback.setComment(dto.comment());
        feedback.setProject(project);

        feedbackRepository.save(feedback);

        // Recalcula a média das notas do projeto
        project.getFeedbacks().add(feedback);
        double average = project.getFeedbacks().stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        project.setAverageRating(Math.round(average * 100.0) / 100.0);
        projectRepository.save(project);

        return feedback;
    }

    // PUT /api/projects/{id}/upvote
    @Transactional
    public Project incrementUpvote(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado com ID: " + projectId));

        project.setUpvotes(project.getUpvotes() == null ? 1L : project.getUpvotes() + 1);
        return projectRepository.save(project);
    }

    // GET /api/projects
    @Transactional(readOnly = true)
    public Page<Project> findAll(String technology, Pageable pageable) {
        if (technology != null && !technology.isBlank()) {
            return projectRepository.findByTechnologyName(technology, pageable);
        }
        return projectRepository.findAll(pageable);
    }
}