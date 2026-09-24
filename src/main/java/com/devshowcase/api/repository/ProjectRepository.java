package com.devshowcase.api.repository;

import com.devshowcase.api.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT DISTINCT p FROM Project p JOIN p.technologies t WHERE (:technology IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', :technology, '%')))")
    Page<Project> findByTechnologyName(@Param("technology") String technology, Pageable pageable);
}