package com.example.task_manager.service;

import com.example.task_manager.dto.CreateTaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.entity.TaskStatus;

import java.util.List;

public interface TaskService {
    TaskResponse create(CreateTaskRequest request);

    List<TaskResponse> getAll();

    TaskResponse updateStatus(
            Long taskId,
            TaskStatus status
    );
}
