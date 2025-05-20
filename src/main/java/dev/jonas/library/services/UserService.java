package dev.jonas.library.services;

import dev.jonas.library.dtos.UserDTO;
import dev.jonas.library.dtos.UserInputDTO;
import dev.jonas.library.entities.User;

import java.util.List;

public interface UserService {
    // GET
    // List<User> getAllUsers();
    List<UserDTO> getAllUserDTOs();
    UserDTO getUserDtoById(Long id);

    // POST
    User addUser(UserInputDTO dto);

    // PUT
    User updateUser(Long id, UserInputDTO dto);

    // DELETE
    void deleteUserById(Long id);
}
