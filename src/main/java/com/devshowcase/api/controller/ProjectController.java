package com.devshowcase.api.controller;

import com.devshowcase.api.dto.ProjectDTO;
import com.devshowcase.api.model.Profile;
import com.devshowcase.api.model.Project;
import com.devshowcase.api.model.Technology;
import com.devshowcase.api.repository.ProfileRepository;
import com.devshowcase.api.repository.ProjectRepository;
import com.devshowcase.api.repository.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjectDTO dto) {
        Profile profile = profileRepository.findById(dto.getProfileId()).orElse(null);
        if (profile == null) {
            return ResponseEntity.badRequest().body("Perfil com o ID fornecido não foi encontrado.");
        }

        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setRepositoryUrl(dto.getRepositoryUrl());
        project.setProfile(profile);

        if (dto.getTechnologyIds() != null && !dto.getTechnologyIds().isEmpty()) {
            List<Technology> techs = technologyRepository.findAllById(dto.getTechnologyIds());
            project.setTechnologies(new HashSet<>(techs));
        }

        Project saved = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody ProjectDTO dto) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(dto.getTitle());
                    project.setDescription(dto.getDescription());
                    project.setRepositoryUrl(dto.getRepositoryUrl());

                    if (dto.getTechnologyIds() != null) {
                        List<Technology> techs = technologyRepository.findAllById(dto.getTechnologyIds());
                        project.setTechnologies(new HashSet<>(techs));
                    }

                    Project updated = projectRepository.save(project);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}