package com.taskservice.backend.entity;

import com.taskservice.backend.enums.TaskPriorityLevel;
import com.taskservice.backend.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task")
@Getter
@Setter
@Where(clause = "active=true")
public class Task {


    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private TaskStatus status;
    @Column
    private TaskPriorityLevel priorityLevel;
    @Column
    private String description;
    @Column
    private LocalDate dueDate;
    @Column
    private String externalId;
    @Column
    private Boolean active;
}
