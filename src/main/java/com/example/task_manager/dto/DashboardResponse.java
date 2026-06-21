package com.example.task_manager.dto;

public record DashboardResponse(
        long totalTasks,
        long completedTasks,
        long pendingTasks
) {}
