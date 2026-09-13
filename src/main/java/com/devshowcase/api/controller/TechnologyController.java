package com.devshowcase.api.controller;

import com.devshowcase.api.dto.TechnologyDTO;
import com.devshowcase.api.model.Technology;
import com.devshowcase.api.repository.TechnologyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    private TechnologyRepository technologyRepository;

    @PostMapping
    public ResponseEntity<Technology> create(@Valid @RequestBody TechnologyDTO dto) {
        Technology tech = new Technology();
        tech.setName(dto.getName());
        Technology saved = technologyRepository.save(tech);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Technology>> getAll() {
        return ResponseEntity.ok(technologyRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technology> getById(@PathVariable Long id) {
        return technologyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!technologyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        technologyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}