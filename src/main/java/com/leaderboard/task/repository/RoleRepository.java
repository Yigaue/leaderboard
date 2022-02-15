package com.leaderboard.task.repository;
import java.util.Optional;

import com.leaderboard.task.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}