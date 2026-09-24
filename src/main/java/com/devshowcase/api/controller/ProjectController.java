package com.devshowcase.api.controller;

import com.devshowcase.api.dto.FeedbackDTO;
import com.devshowcase.api.model.Feedback;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // 1. GET /api/projects?technology=java&page=0&size=10
    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(required = false) String technology,
            @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        Page<Project> projects = projectService.findAll(technology, pageable);
        return ResponseEntity.ok(projects);
    }

    // 2. POST /api/projects/{id}/feedbacks
    @PostMapping("/{id}/feedbacks")
    public ResponseEntity<Feedback> addFeedback(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackDTO dto) {
        Feedback feedback = projectService.addFeedback(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(feedback);
    }

    // 3. PUT /api/projects/{id}/upvote
    @PutMapping("/{id}/upvote")
    public ResponseEntity<Project> upvoteProject(@PathVariable Long id) {
        Project updatedProject = projectService.incrementUpvote(id);
        return ResponseEntity.ok(updatedProject);
    }
}