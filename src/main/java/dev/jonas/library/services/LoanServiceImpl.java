package dev.jonas.library.services;

import dev.jonas.library.dtos.loan.LoanCreateDTO;
import dev.jonas.library.dtos.loan.LoanDTO;
import dev.jonas.library.entities.Book;
import dev.jonas.library.entities.Loan;
import dev.jonas.library.entities.User;
import dev.jonas.library.exceptions.BookUnavailableException;
import dev.jonas.library.exceptions.LoanAlreadyReturnedException;
import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;
import dev.jonas.library.repositories.BookRepository;
import dev.jonas.library.repositories.LoanRepository;
import dev.jonas.library.repositories.UserRepository;
import dev.jonas.library.utils.EntityFetcher;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookServiceImpl bookServiceImpl;

    public LoanServiceImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository, BookServiceImpl bookServiceImpl) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookServiceImpl = bookServiceImpl;
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
    @Transactional
    public LoanDTO addLoan(LoanCreateDTO dto) {
        User user = EntityFetcher.getUserOrThrow(dto.getUserId(), userRepository);
        Book book = EntityFetcher.getBookOrThrow(dto.getBookId(), bookRepository);

        Loan loan = DtoToEntityMapper.mapToLoanEntity(dto, user, book);

        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("No available copies of this book.");
        }
        bookServiceImpl.decrementAvailableCopies(book.getBookId());


        loan.setDueDate(LocalDate.now().plusDays(14));

        Loan savedLoan = loanRepository.save(loan);

        return EntityToDtoMapper.mapToLoanDto(savedLoan);
    }

    @Override
    @Transactional
    public LoanDTO extendLoan(Long loanId) {
        Loan loan = EntityFetcher.getLoanOrThrow(loanId, loanRepository);

        if (loan.getReturnedDate() != null) {
            throw new LoanAlreadyReturnedException("Cannot extend a returned loan.");
        }

        loan.setDueDate(LocalDate.now().plusDays(14));

        Loan savedLoan = loanRepository.save(loan);
        return EntityToDtoMapper.mapToLoanDto(savedLoan);
    }

    @Override
    @Transactional
    public LoanDTO returnLoan(Long loanId) {
        Loan loan = EntityFetcher.getLoanOrThrow(loanId, loanRepository);

        if (loan.getReturnedDate() != null) {
            throw new LoanAlreadyReturnedException("Loan has already been returned.");
        }

        loan.setReturnedDate(LocalDate.now());
        bookServiceImpl.incrementAvailableCopies(loan.getBook().getBookId());

        Loan savedLoan = loanRepository.save(loan);
        return EntityToDtoMapper.mapToLoanDto(savedLoan);
    }

}
