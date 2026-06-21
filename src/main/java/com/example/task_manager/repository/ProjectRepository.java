package com.example.task_manager.repository;

import com.example.task_manager.entity.Project;
import com.example.task_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
}
