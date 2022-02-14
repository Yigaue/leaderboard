package com.leaderboard.task.controller;

import java.util.List;
import java.util.Optional;

import com.leaderboard.task.model.Task;
import com.leaderboard.task.repository.TaskRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Task> getTask(@PathVariable Long id) {
        return taskRepository.findById(id);
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Record deleted: " + id;
    }

    @PatchMapping("{id}")
    public Task update(@PathVariable Long id, @RequestBody Task task) {
       Optional<Task> taskRecord = taskRepository.findById(id);
        if(taskRecord.isPresent()) {
           Task taskFound =  taskRecord.get();
           BeanUtils.copyProperties(task, taskFound, "id");
           return taskRepository.save(taskFound);
        }

        return task;
    }
}