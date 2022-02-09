package com.leaderboard.task.repository;

import com.leaderboard.task.model.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}