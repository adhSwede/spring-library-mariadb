package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserInputDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
