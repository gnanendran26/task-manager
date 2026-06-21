package com.example.task_manager.service.impl;

import com.example.task_manager.dto.CreateProjectRequest;
import com.example.task_manager.dto.ProjectResponse;
import com.example.task_manager.entity.Project;
import com.example.task_manager.entity.User;
import com.example.task_manager.repository.ProjectRepository;
import com.example.task_manager.service.ProjectService;
import com.example.task_manager.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse create(CreateProjectRequest request) {

        User user = SecurityUtil.getCurrentUser();

        Project project = Project.builder()
                .name(request.name())
                .description(request.description())
                .createdAt(LocalDateTime.now())
                .owner(user)
                .build();

        project = projectRepository.save(project);

        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCreatedAt()
        );
    }

    @Override
    public List<ProjectResponse> getMyProjects() {
        User user = SecurityUtil.getCurrentUser();

        return projectRepository.findByOwner(user)
                .stream()
                .map(p -> new ProjectResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getCreatedAt()))
                .toList();

    }
}
