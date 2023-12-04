package com.taskservice.backend.services.interfaces;

import com.taskservice.backend.dtos.requests.TaskRequest;
import com.taskservice.backend.dtos.requests.UpdateTaskRequest;
import com.taskservice.backend.dtos.responses.TaskModel;
import com.taskservice.backend.dtos.responses.TasksPaginatedResponse;
import com.taskservice.backend.enums.SortByAttribute;
import com.taskservice.backend.enums.SortingOrder;

public interface ITaskService {
    TaskModel addTask(TaskRequest taskRequest);

    TaskModel updateTask(String taskId, UpdateTaskRequest taskRequest);

    void deleteTask(String taskId);

    TasksPaginatedResponse getTasks(SortByAttribute sortByAttribute, Integer page, Integer limit,
                                    SortingOrder sortingOrder);
}
