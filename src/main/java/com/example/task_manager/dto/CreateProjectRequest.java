package com.example.task_manager.dto;

public record CreateProjectRequest(
        String name,
        String description
) {}
