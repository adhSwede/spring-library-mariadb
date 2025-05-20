package dev.jonas.library.services;

import dev.jonas.library.dtos.BookWithAuthorDTO;
import dev.jonas.library.entities.Book;
import dev.jonas.library.exceptions.BookNotFoundException;
import dev.jonas.library.repositories.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookWithAuthorDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> new BookWithAuthorDTO(
                        book.getTitle(),
                        book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()
                ))
                .toList();
    }

    @Override
    public BookWithAuthorDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));

        return new BookWithAuthorDTO(
                book.getTitle(),
                book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName()
        );
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setPublicationYear(updatedBook.getPublicationYear());
                    existingBook.setTotalCopies(updatedBook.getTotalCopies());
                    existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public void decrementAvailableCopies(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));

        if  (book.getAvailableCopies() <= 0){
            throw new IllegalStateException("Book with ID " + bookId + " has no available copies");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    };

    public void incrementAvailableCopies(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }
}
