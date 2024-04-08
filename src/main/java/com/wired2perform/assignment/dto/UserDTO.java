package com.wired2perform.assignment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import lombok.*;

/**
 * UserDTO class
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    private String telephoneNumber;

    private int age;

}
