package dev.jonas.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanInputDTO {
    private Long userId;
    private Long bookId;
}