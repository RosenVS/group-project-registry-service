package com.openmaze.registryservice.service;

import com.openmaze.registryservice.dto.ProjectResponse;
import com.openmaze.registryservice.dto.RegisterProjectRequest;
import com.openmaze.registryservice.dto.UpdateProjectRequest;
import com.openmaze.registryservice.model.Project;
import com.openmaze.registryservice.model.ProjectType;
import com.openmaze.registryservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectResponse registerProject(RegisterProjectRequest registerProjectRequest) {
        Project project = Project.builder()
                .name(registerProjectRequest.getName())
                .organization(registerProjectRequest.getOrganization())
                .url(registerProjectRequest.getUrl())
                .projectType(ProjectType.valueOf(registerProjectRequest.getProjectType().toUpperCase()))
                .build();

        Project savedProject = projectRepository.save(project);
        return mapToProjectResponse(savedProject);
    }

    public ProjectResponse getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        return mapToProjectResponse(project);
    }

    public List<ProjectResponse> getProjectsByProjectType(String projectType) {
        ProjectType type = ProjectType.valueOf(projectType.toUpperCase());
        List<Project> projects = projectRepository.findByProjectType(type);
        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    public List<ProjectResponse> getProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    public ProjectResponse updateProject(Long projectId, UpdateProjectRequest updateProjectRequest) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(updateProjectRequest.getName());
        project.setOrganization(updateProjectRequest.getOrganization());
        project.setUrl(updateProjectRequest.getUrl());
        project.setProjectType(ProjectType.valueOf(updateProjectRequest.getProjectType().toUpperCase()));
        Project savedProject = projectRepository.save(project);
        return mapToProjectResponse(savedProject);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .organization(project.getOrganization())
                .url(project.getUrl())
                .projectType(project.getProjectType().name())
                .build();
    }
}
