package com.example.task_manager.controller;

import com.example.task_manager.dto.DashboardResponse;
import com.example.task_manager.entity.TaskStatus;
import com.example.task_manager.entity.User;
import com.example.task_manager.repository.TaskRepository;
import com.example.task_manager.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DashboardController {

    private final TaskRepository taskRepository;

    @GetMapping("/dashboard")
    public DashboardResponse dashboard() {

        User user = SecurityUtil.getCurrentUser();

        long total = taskRepository.countByProjectOwner(user);

        long completed = taskRepository.countByProjectOwnerAndStatus(user, TaskStatus.COMPLETED);

        long pending = taskRepository.countByProjectOwnerAndStatus(user, TaskStatus.TODO) +
                       taskRepository.countByProjectOwnerAndStatus(user, TaskStatus.IN_PROGRESS);

        return new DashboardResponse(
                total,
                completed,
                pending
        );
    }
}
