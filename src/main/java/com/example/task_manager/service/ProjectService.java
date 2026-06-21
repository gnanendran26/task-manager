package com.example.task_manager.service;

import com.example.task_manager.dto.CreateProjectRequest;
import com.example.task_manager.dto.ProjectResponse;

import java.util.List;

public interface ProjectService {

    ProjectResponse create(CreateProjectRequest request);

    List<ProjectResponse> getMyProjects();
}
