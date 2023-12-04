package com.taskservice.backend.dtos.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.taskservice.backend.enums.TaskPriorityLevel;
import com.taskservice.backend.enums.TaskStatus;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskRequest {
    private String name;
    private String description;
    private TaskPriorityLevel taskPriorityLevel;
    private TaskStatus taskStatus;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dueDate;
}
