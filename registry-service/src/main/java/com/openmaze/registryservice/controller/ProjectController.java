package com.openmaze.registryservice.controller;

import com.openmaze.registryservice.dto.ProjectResponse;
import com.openmaze.registryservice.dto.RegisterProjectRequest;
import com.openmaze.registryservice.dto.UpdateProjectRequest;
import com.openmaze.registryservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ProjectResponse registerProject(@RequestBody RegisterProjectRequest registerProjectRequest) {
        return projectService.registerProject(registerProjectRequest);
    }

    @GetMapping("/{projectId}")
    public ProjectResponse getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping("/type/{projectType}")
    public List<ProjectResponse> getProjectsByProjectType(@PathVariable String projectType) {
        return projectService.getProjectsByProjectType(projectType);
    }

    @GetMapping
    public List<ProjectResponse> getProjects() {
        return projectService.getProjects();
    }

    @PutMapping("/{projectId}")
    public ProjectResponse updateProject(@PathVariable Long projectId, @RequestBody UpdateProjectRequest updateProjectRequest) {
        return projectService.updateProject(projectId, updateProjectRequest);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
