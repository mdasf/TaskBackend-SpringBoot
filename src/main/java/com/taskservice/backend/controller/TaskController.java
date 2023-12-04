package com.taskservice.backend.controller;


import com.taskservice.backend.dtos.requests.TaskRequest;
import com.taskservice.backend.dtos.requests.UpdateTaskRequest;
import com.taskservice.backend.dtos.responses.TaskModel;
import com.taskservice.backend.dtos.responses.TasksPaginatedResponse;
import com.taskservice.backend.enums.SortByAttribute;
import com.taskservice.backend.enums.SortingOrder;
import com.taskservice.backend.services.interfaces.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@Controller
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

//    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
//    public void handleOptions() {
//        return ok;
//    }
    @PostMapping
    public ResponseEntity<TaskModel> addTask(@RequestBody TaskRequest taskRequest) {
        System.out.println("inside addtask");
        return ok(taskService.addTask(taskRequest));
    }

    @GetMapping
    public ResponseEntity<TasksPaginatedResponse>
    getTasks(@RequestParam(value = "sort_by", required = false) SortByAttribute sortByAttribute,
             @RequestParam(value = "page", defaultValue = "0") Integer page,
             @RequestParam(value = "limit", defaultValue = "10") Integer limit,
             @RequestParam(value = "sorting_order", required = false) SortingOrder sortingOrder) {
        return ok(taskService.getTasks(sortByAttribute, page, limit, sortingOrder));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable("taskId") String taskId,
                                @RequestBody UpdateTaskRequest updateTaskRequest) {
        return ok(taskService.updateTask(taskId, updateTaskRequest));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") String taskId) {
        taskService.deleteTask(taskId);
        return ok().build();
    }
}
