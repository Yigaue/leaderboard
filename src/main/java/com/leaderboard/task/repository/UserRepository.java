package com.leaderboard.task.repository;

import com.leaderboard.task.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}