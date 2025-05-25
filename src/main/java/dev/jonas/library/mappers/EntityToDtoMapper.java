package dev.jonas.library.mappers;

import dev.jonas.library.dtos.*;
import dev.jonas.library.entities.*;

public class EntityToDtoMapper {

    // ########## [ Authors ] ##########
    public static AuthorDTO mapToAuthorDto(Author author) {
        return new AuthorDTO(
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthYear(),
                author.getNationality()
        );
    }

    // ########## [ Books ] ##########
    public static BookDetailsDTO mapToBookDetailsDto(Book book) {
        return new BookDetailsDTO(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName(),
                book.getPublicationYear(),
                book.getTotalCopies(),
                book.getAvailableCopies()
        );
    }

    // ########## [ Loans ] ##########
    public static LoanDTO mapToLoanDto(Loan loan) {
        return new LoanDTO(
                loan.getLoanId(),
                mapToBookDetailsDto(loan.getBook()),
                mapToUserDto(loan.getUser()),
                loan.getBorrowedDate(),
                loan.getDueDate(),
                loan.getReturnedDate()
        );
    }

    // ########## [ Users ] ##########
    public static UserDTO mapToUserDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRegistrationDate()
        );
    }
}
