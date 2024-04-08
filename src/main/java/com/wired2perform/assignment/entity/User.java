package com.wired2perform.assignment.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * User Entity class
 */
@Entity
@Table(name = "user_account")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "age")
    private int age;

}
