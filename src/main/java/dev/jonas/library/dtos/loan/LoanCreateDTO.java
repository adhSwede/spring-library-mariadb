package dev.jonas.library.dtos.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoanCreateDTO {
    private Long userId;
    private Long bookId;
}