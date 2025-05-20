package dev.jonas.library.services;

import dev.jonas.library.entities.Loan;
import dev.jonas.library.exceptions.LoanNotFoundException;
import dev.jonas.library.repositories.LoanRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Optional<Loan> getLoanById(Long loanId) {
        return loanRepository.findById(loanId);
    }

    @Override
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan updateLoan(Long Id, Loan updatedLoan) {
        return loanRepository.findById(Id).map(
                existingLoan -> {
                    existingLoan.setBook(updatedLoan.getBook());
                    existingLoan.setUser(updatedLoan.getUser());
                    existingLoan.setBorrowedDate(updatedLoan.getBorrowedDate());
                    existingLoan.setDueDate(updatedLoan.getDueDate());
                    existingLoan.setReturnedDate(updatedLoan.getReturnedDate());
                    return loanRepository.save(existingLoan);
                })
                .orElseThrow(() -> new LoanNotFoundException("Loan with ID " + Id + " not found"));
    }

    @Override
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new LoanNotFoundException("Loan with ID " + id + " not found");
        }
        loanRepository.deleteById(id);
    }

}
