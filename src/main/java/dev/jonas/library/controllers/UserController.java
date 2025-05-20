package dev.jonas.library.controllers;

import dev.jonas.library.dtos.UserDTO;
import dev.jonas.library.dtos.UserInputDTO;
import dev.jonas.library.entities.User;
import dev.jonas.library.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ########## [ GET ] ##########
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUserDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    // ########## [ POST ] ##########
    @PostMapping
    public User addUser(@RequestBody UserInputDTO dto) {
        return userService.addUser(dto);
    }

    // ########## [ PUT ] ##########
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserInputDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // ########## [ DELETE ] ##########
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}