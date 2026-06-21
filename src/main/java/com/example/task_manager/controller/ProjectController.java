package com.example.task_manager.controller;

import com.example.task_manager.dto.CreateProjectRequest;
import com.example.task_manager.dto.ProjectResponse;
import com.example.task_manager.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ProjectResponse create(@Valid @RequestBody CreateProjectRequest request) {

        return projectService.create(request);
    }

    @GetMapping("/list")
    public List<ProjectResponse> getMyProjects() {
        return projectService.getMyProjects();
    }
}
