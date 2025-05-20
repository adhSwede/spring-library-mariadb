package dev.jonas.library.services;

import dev.jonas.library.entities.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    // GET
    List<Loan> getAllLoans();
    Optional<Loan> getLoanById(Long id);

    // POST
    Loan addLoan(Loan loan);

    // PUT
    Loan updateLoan(Long id, Loan loan);

    // DELETE
    void deleteLoan(Long id);
}
