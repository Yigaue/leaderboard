package com.leaderboard.task.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60)
    private String name;
}