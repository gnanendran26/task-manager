package com.example.task_manager.controller;

import com.example.task_manager.dto.CreateTaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.dto.UpdateTaskRequest;
import com.example.task_manager.entity.TaskStatus;
import com.example.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping("/updatestatus/{id}/status")
    public TaskResponse updateStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return taskService.updateStatus(id, status);
    }

    @PutMapping("update/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<TaskResponse> getTasks(

            @RequestParam(required = false)
            TaskStatus status,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return taskService.getTasks(status, page, size);
    }
}