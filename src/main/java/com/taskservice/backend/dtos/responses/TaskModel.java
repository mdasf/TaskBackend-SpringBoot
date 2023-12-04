package com.taskservice.backend.dtos.responses;


import com.taskservice.backend.entity.Task;
import com.taskservice.backend.enums.TaskPriorityLevel;
import com.taskservice.backend.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {

    private String taskId;
    private String name;
    private String description;
    private TaskPriorityLevel taskPriorityLevel;
    private TaskStatus taskStatus;
    private LocalDate dueDate;

    public static TaskModel from(Task task ) {
        return TaskModel.builder()
                .taskStatus(task.getStatus())
                .taskId(task.getExternalId())
                .description(task.getDescription())
                .name(task.getName())
                .taskPriorityLevel(task.getPriorityLevel())
                .dueDate(task.getDueDate()).build();
    }
}
