package com.leaderboard.task.controller;

import java.util.List;
import java.util.Optional;

import com.leaderboard.task.model.Task;
import com.leaderboard.task.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}