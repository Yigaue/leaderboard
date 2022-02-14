package com.leaderboard.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveUser(@Validated @RequestBody User user) {
        User saveUser = userRepository.save(user);
        return "user saved " + saveUser.getId();
    }
}