package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class LoanDTO {
    private Long id;
    private BookDetailsDTO book;
    private UserDTO user;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
}