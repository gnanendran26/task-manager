package com.example.task_manager.service.impl;

import com.example.task_manager.dto.CreateTaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.entity.Project;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.TaskPriority;
import com.example.task_manager.entity.TaskStatus;
import com.example.task_manager.repository.ProjectRepository;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl
        implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public TaskResponse create(CreateTaskRequest request) {

        Project project = projectRepository.findById(
                        request.projectId())
                .orElseThrow();

        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .priority(TaskPriority.valueOf(request.priority()))
                .status(TaskStatus.TODO)
                .dueDate(request.dueDate())
                .createdAt(LocalDateTime.now())
                .project(project)
                .build();

        task = taskRepository.save(task);

        return map(task);
    }

    @Override
    public List<TaskResponse> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public TaskResponse updateStatus(
            Long taskId,
            TaskStatus status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow();

        task.setStatus(status);

        taskRepository.save(task);

        return map(task);
    }

    private TaskResponse map(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getPriority().name(),
                task.getDueDate()
        );
    }
}
