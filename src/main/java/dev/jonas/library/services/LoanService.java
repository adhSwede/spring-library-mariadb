package dev.jonas.library.services;

import dev.jonas.library.dtos.LoanDTO;
import dev.jonas.library.dtos.LoanInputDTO;
import dev.jonas.library.entities.Book;
import dev.jonas.library.entities.User;

import java.util.List;

public interface LoanService {
    // GET
    List<LoanDTO> getAllLoanDTOs();

    LoanDTO getLoanById(Long loanId);

    // POST
    LoanDTO addLoan(LoanInputDTO dto, User user, Book book);

    // PUT
    LoanDTO updateLoan(Long loanId, LoanInputDTO dto);

    // DELETE
    void deleteLoan(Long id);
}
