package com.example.task_manager.controller;

import com.example.task_manager.dto.CreateTaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.entity.TaskStatus;
import com.example.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest request) {

        return taskService.create(request);
    }

    @GetMapping("/list")
    public List<TaskResponse> getAll() {
        return taskService.getAll();
    }

    @PatchMapping("/updatetask/{id}/status")
    public TaskResponse updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return taskService.updateStatus(id, status);
    }
}