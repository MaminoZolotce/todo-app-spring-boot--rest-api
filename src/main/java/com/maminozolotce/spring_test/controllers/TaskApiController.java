package com.maminozolotce.spring_test.controllers;

import com.maminozolotce.spring_test.entity.Task;
import com.maminozolotce.spring_test.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/records")
@AllArgsConstructor
public class TaskApiController {
    private TaskService taskService;

    @GetMapping
    public List<Task> getAll(){
        return taskService.findAllRecords();
    }
    
}
