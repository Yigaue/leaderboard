package com.leaderboard.task.controller;

import java.lang.System.Logger;
import java.util.Collections;

import javax.validation.Valid;

import com.leaderboard.task.dto.LoginDTO;
import com.leaderboard.task.dto.SignupDTO;
import com.leaderboard.task.model.User;
import com.leaderboard.task.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.leaderboard.task.model.Role;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("User login successfully", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignupDTO signupDTO) {

        if(Boolean.TRUE.equals(userRepository.existsByEmail(signupDTO.getEmail()))) {
            return new ResponseEntity<>("User already exist", HttpStatus.BAD_REQUEST);
        }

        User user = new User(signupDTO.getFirstName(),
            signupDTO.getLastName(),
            signupDTO.getEmail(),
            passwordEncoder.encode(signupDTO.getPassword())
        );

        Role roles = roleRepository.findByName("ADMIN").get();

        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}