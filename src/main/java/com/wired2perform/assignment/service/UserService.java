package com.wired2perform.assignment.service;

import com.wired2perform.assignment.dto.UserDTO;
import com.wired2perform.assignment.entity.User;
import com.wired2perform.assignment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO userDTO) {

        User existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
           throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

}
