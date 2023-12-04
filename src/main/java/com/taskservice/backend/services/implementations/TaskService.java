package com.taskservice.backend.services.implementations;


import com.mysql.cj.util.StringUtils;
import com.taskservice.backend.dtos.requests.TaskRequest;
import com.taskservice.backend.dtos.requests.UpdateTaskRequest;
import com.taskservice.backend.dtos.responses.TaskModel;
import com.taskservice.backend.dtos.responses.TasksPaginatedResponse;
import com.taskservice.backend.entity.Task;
import com.taskservice.backend.enums.SortByAttribute;
import com.taskservice.backend.enums.SortingOrder;
import com.taskservice.backend.jpa.ITaskDao;
import com.taskservice.backend.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.taskservice.backend.enums.SortByAttribute.TASK_PRIORITY;
import static com.taskservice.backend.enums.SortByAttribute.TASK_STATUS;

@Component
@Transactional
public class TaskService implements ITaskService {

    @Autowired
    private ITaskDao iTaskDao;
    @Override
    public TaskModel addTask(TaskRequest taskRequest) {
        Task task = Task.builder()
                .status(taskRequest.getTaskStatus())
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .externalId(UUID.randomUUID().toString())
                .priorityLevel(taskRequest.getTaskPriorityLevel())
                .dueDate(taskRequest.getDueDate())
                .active(true)
                .build();
        Task taskSaved = iTaskDao.save(task);
        return TaskModel.from(taskSaved);
    }

    @Override
    public TaskModel updateTask(String taskId, UpdateTaskRequest updateTaskRequest) {
        Task task = iTaskDao.findByExternalId(taskId);
        if (!StringUtils.isNullOrEmpty(updateTaskRequest.getName())) {
            task.setName(updateTaskRequest.getName());
        }
        if (!StringUtils.isNullOrEmpty(updateTaskRequest.getDescription())) {
            task.setDescription(updateTaskRequest.getDescription());
        }
        if (!ObjectUtils.isEmpty(updateTaskRequest.getTaskStatus())) {
            task.setStatus(updateTaskRequest.getTaskStatus());
        }
        if (!ObjectUtils.isEmpty(updateTaskRequest.getTaskPriorityLevel())) {
            task.setPriorityLevel(updateTaskRequest.getTaskPriorityLevel());
        }
        if (!ObjectUtils.isEmpty(updateTaskRequest.getDueDate())) {
            task.setDueDate(updateTaskRequest.getDueDate());
        }
        return TaskModel.from(task);
    }

    @Override
    public void deleteTask(String taskId) {
        Task task = iTaskDao.findByExternalId(taskId);
        task.setActive(false);
        iTaskDao.save(task);
    }

    @Override
    public TasksPaginatedResponse getTasks(SortByAttribute sortByAttribute, Integer page, Integer limit,
                                           SortingOrder sortingOrder) {
        Pageable pageable;
        if (ObjectUtils.isEmpty(sortingOrder)) {
            pageable = PageRequest.of(page, limit);
        } else {
            pageable = PageRequest.of(page, limit,
                    sortingOrder == SortingOrder.ASCENDING
                            ? getSort(sortByAttribute).ascending()
                            : getSort(sortByAttribute).descending());
        }

        Page<Task> pageTasks = iTaskDao.findAll(pageable);
        List<TaskModel> taskModelList = pageTasks.stream().map(TaskModel::from).collect(Collectors.toList());
        return TasksPaginatedResponse.builder()
                .totalPages((long)pageTasks.getTotalPages())
                .currentPage(Long.valueOf(page))
                .limit(Long.valueOf(limit))
                .tasks(taskModelList).build();
    }

    private Sort getSort(SortByAttribute sortByAttribute) {
        return Sort.by(getAttributeName(sortByAttribute));
    }

    private String getAttributeName(SortByAttribute sortByAttribute) {
        if (TASK_PRIORITY.equals(sortByAttribute)) return "priorityLevel" ;
        if (TASK_STATUS.equals(sortByAttribute)) return "status";
        throw new RuntimeException("Unrecognized attribute sortByAttribute: " + sortByAttribute);
    }


}
