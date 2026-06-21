package com.example.task_manager.repository;

import com.example.task_manager.entity.Project;
import com.example.task_manager.entity.Task;
import com.example.task_manager.entity.TaskStatus;
import com.example.task_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByProjectOwner(User owner);

    Page<Task> findByProjectOwner(
            User owner,
            Pageable pageable
    );

    Page<Task> findByProjectOwnerAndStatus(
            User owner,
            TaskStatus status,
            Pageable pageable
    );
}
