package com.wired2perform.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Todos Entity class
 */
@Entity
@Table(name = "todos")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ElementCollection
    @CollectionTable(name = "todo_list", joinColumns = @JoinColumn(name = "todo_id"))
    @Column(name = "todo")
    private List<String> todos;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "archived")
    private boolean archived;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}

