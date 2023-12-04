package com.taskservice.backend.jpa;

import com.taskservice.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskDao extends JpaRepository<Task, Long> {

    Task findByExternalId(String taskExternalId);
}
