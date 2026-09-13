package com.devshowcase.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devshowcase.api.dto.FeedbackDTO;
import com.devshowcase.api.model.Feedback;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.repository.FeedbackRepository;
import com.devshowcase.api.repository.ProjectRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects/{projectId}/feedbacks")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    private final ProjectRepository projectRepository;

    FeedbackController(FeedbackRepository feedbackRepository, ProjectRepository projectRepository) {
        this.feedbackRepository = feedbackRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@PathVariable Long projectId, @Valid @RequestBody FeedbackDTO dto) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.badRequest().body("Projeto não encontrado para registrar o feedback.");
        }

        Feedback feedback = new Feedback();
        feedback.setComment(dto.getComment());
        feedback.setProject(project);

        Feedback saved = feedbackRepository.save(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<?> getByProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project.getFeedbacks());
    }
}