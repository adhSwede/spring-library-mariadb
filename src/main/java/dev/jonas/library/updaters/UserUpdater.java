package dev.jonas.library.updaters;

import dev.jonas.library.dtos.user.UserInputDTO;
import dev.jonas.library.entities.User;

public class UserUpdater {
    public static void apply(User user, UserInputDTO dto) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
    }
}
