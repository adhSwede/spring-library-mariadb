package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthorDTO {
    private Long authorId;
    private String firstName;
    private String lastName;
    private Integer birthYear;
    private String nationality;
}