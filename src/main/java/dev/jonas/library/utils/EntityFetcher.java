package dev.jonas.library.utils;

import dev.jonas.library.entities.Author;
import dev.jonas.library.entities.Book;
import dev.jonas.library.entities.Loan;
import dev.jonas.library.entities.User;

import dev.jonas.library.exceptions.AuthorNotFoundException;
import dev.jonas.library.exceptions.BookNotFoundException;
import dev.jonas.library.exceptions.LoanNotFoundException;
import dev.jonas.library.exceptions.UserNotFoundException;

import dev.jonas.library.repositories.AuthorRepository;
import dev.jonas.library.repositories.BookRepository;
import dev.jonas.library.repositories.LoanRepository;
import dev.jonas.library.repositories.UserRepository;

public class EntityFetcher {

    public static Author getAuthorOrThrow(Long authorId, AuthorRepository repo) {
        return repo.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + authorId + " not found"));
    }

    public static Book getBookOrThrow(Long bookId, BookRepository repo) {
        return repo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
    }

    public static User getUserOrThrow(Long userId, UserRepository repo) {
        return repo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

    public static Loan getLoanOrThrow(Long loanId, LoanRepository repo) {
        return repo.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan with ID " + loanId + " not found"));
    }

}

