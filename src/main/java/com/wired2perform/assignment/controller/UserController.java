package com.wired2perform.assignment.controller;

import com.wired2perform.assignment.dto.UserDTO;
import com.wired2perform.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController provides method for User related operations
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Create new user account for given details
     * @param userDTO userDTO with new user details
     * @return newly created user details
     */
    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody(required = true) UserDTO userDTO) {
        UserDTO newUser = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
