package dev.jonas.library.services;

import dev.jonas.library.dtos.LoanDTO;
import dev.jonas.library.dtos.LoanInputDTO;
import dev.jonas.library.entities.Book;
import dev.jonas.library.entities.Loan;
import dev.jonas.library.entities.User;
import dev.jonas.library.exceptions.LoanNotFoundException;
import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;
import dev.jonas.library.repositories.BookRepository;
import dev.jonas.library.repositories.LoanRepository;
import dev.jonas.library.repositories.UserRepository;
import dev.jonas.library.utils.EntityFetcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<LoanDTO> getAllLoanDTOs() {
        return loanRepository.findAll().stream()
                .map(EntityToDtoMapper::mapToLoanDto)
                .toList();
    }

    @Override
    public LoanDTO getLoanById(Long loanId) {
        Loan loan = EntityFetcher.getLoanOrThrow(loanId, loanRepository);

        return EntityToDtoMapper.mapToLoanDto(loan);
    }

    @Override
    public LoanDTO addLoan(LoanInputDTO dto, User user, Book book) {
        Loan loan = DtoToEntityMapper.mapToLoanEntity(dto, user, book);
        Loan savedLoan = loanRepository.save(loan);

        return EntityToDtoMapper.mapToLoanDto(savedLoan);
    }

    @Override
    public LoanDTO updateLoan(Long loanId, LoanInputDTO dto) {
        Loan updatedLoan = EntityFetcher.getLoanOrThrow(loanId, loanRepository);

        User user = EntityFetcher.getUserOrThrow(dto.getUserId(), userRepository);
        Book book = EntityFetcher.getBookOrThrow(dto.getBookId(), bookRepository);

        updatedLoan.setUser(user);
        updatedLoan.setBook(book);
        updatedLoan.setReturnedDate(LocalDate.now());
        updatedLoan.setDueDate(LocalDate.now().plusDays(14));

        Loan savedLoan = loanRepository.save(updatedLoan);
        return EntityToDtoMapper.mapToLoanDto(savedLoan);
    }

    @Override
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new LoanNotFoundException("Loan with ID " + id + " not found");
        }
        loanRepository.deleteById(id);
    }

}
