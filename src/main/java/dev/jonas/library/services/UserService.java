package dev.jonas.library.services;

import dev.jonas.library.dtos.user.UserDTO;
import dev.jonas.library.dtos.user.UserInputDTO;

import java.util.List;

public interface UserService {
    // GET
    // List<User> getAllUsers();
    List<UserDTO> getAllUserDTOs();
    UserDTO getUserDtoById(Long id);

    // POST
    UserDTO addUser(UserInputDTO dto);

    // PUT
    UserDTO updateUser(Long id, UserInputDTO dto);

    // DELETE
    void deleteUserById(Long id);
}
