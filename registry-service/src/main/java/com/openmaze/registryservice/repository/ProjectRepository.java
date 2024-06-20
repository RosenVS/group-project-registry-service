package com.openmaze.registryservice.repository;

import com.openmaze.registryservice.model.Project;
import com.openmaze.registryservice.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.projectType = :projectType")
    List<Project> findByProjectType(@Param("projectType") ProjectType projectType);
}
