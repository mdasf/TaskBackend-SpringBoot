package com.taskservice.backend.dtos.responses;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasksPaginatedResponse {
    private List<TaskModel> tasks;
    private Long totalPages;
    private Long currentPage;
    private Long limit;
}
