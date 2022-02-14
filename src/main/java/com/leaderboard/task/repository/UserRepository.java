package com.leaderboard.task.repository;

import java.util.Optional;

import com.leaderboard.task.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}