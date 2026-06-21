package com.example.task_manager.service;

import com.example.task_manager.dto.CreateTaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.dto.UpdateTaskRequest;
import com.example.task_manager.entity.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    TaskResponse create(CreateTaskRequest request);

    List<TaskResponse> getAll();

    TaskResponse updateStatus(
            Long taskId,
            TaskStatus status
    );

    TaskResponse update(Long taskId, UpdateTaskRequest request);

    void delete(Long taskId);

    Page<TaskResponse> getTasks(
            TaskStatus status,
            int page,
            int size
    );
}
