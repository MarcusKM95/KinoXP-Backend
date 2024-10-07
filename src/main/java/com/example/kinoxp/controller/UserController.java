package com.example.kinoxp.controller;

import com.example.kinoxp.dto.LoginDTO;
import com.example.kinoxp.dto.UserDTO;
import com.example.kinoxp.mapper.UserMapper;
import com.example.kinoxp.model.User;
import com.example.kinoxp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    // Opret bruger (Registrering)
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User savedUser = userService.createUser(user);
        UserDTO responseDTO = userMapper.toUserDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Hent alle brugere
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    // Hent bruger efter ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(userMapper.toUserDTO(user)) : ResponseEntity.notFound().build();
    }

    // Opdater bruger
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        User userDetails = userMapper.toUser(userDTO);
        User updatedUser = userService.updateUser(id, userDetails);
        return updatedUser != null ? ResponseEntity.ok(userMapper.toUserDTO(updatedUser)) : ResponseEntity.notFound().build();
    }

    // Slet bruger
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null) {
            return ResponseEntity.ok("Login successful"); // Return a token or user info if needed
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
