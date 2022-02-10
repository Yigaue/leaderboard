package com.leaderboard.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leaderboard.task.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import com.leaderboard.task.model.User;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}