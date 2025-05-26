package dev.jonas.library.services;

import dev.jonas.library.dtos.book.BookDetailsDTO;
import dev.jonas.library.dtos.book.BookInputDTO;
import dev.jonas.library.entities.Author;
import dev.jonas.library.entities.Book;
import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;
import dev.jonas.library.repositories.AuthorRepository;
import dev.jonas.library.repositories.BookRepository;
import dev.jonas.library.utils.EntityFetcher;
import dev.jonas.library.utils.InputValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDetailsDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(EntityToDtoMapper::mapToBookDetailsDto)
                .toList();
    }

    @Override
    public BookDetailsDTO getBookById(Long bookId) {
        Book book = EntityFetcher.getBookOrThrow(bookId, bookRepository);

        return EntityToDtoMapper.mapToBookDetailsDto(book);
    }

    @Override
    public List<BookDetailsDTO> searchBooks(String title, String author) {
        InputValidator.requireAtLeastOneSearchParam(title, author);

        List<Book> results = bookRepository.searchBooks(title, author);

        return results.stream()
                .map(EntityToDtoMapper::mapToBookDetailsDto)
                .toList();

    }

    @Override
    public BookDetailsDTO addBook(BookInputDTO dto) {
        Author author = EntityFetcher.getAuthorOrThrow(dto.getAuthorId(), authorRepository);

        Book book = DtoToEntityMapper.mapToBookEntity(dto, author);
        Book savedBook = bookRepository.save(book);

        return EntityToDtoMapper.mapToBookDetailsDto(savedBook);
    }

    public void decrementAvailableCopies(Long bookId) {
        Book book = EntityFetcher.getBookOrThrow(bookId, bookRepository);

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("Book with ID " + bookId + " has no available copies");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
    }

    public void incrementAvailableCopies(Long bookId) {
        Book book = EntityFetcher.getBookOrThrow(bookId, bookRepository);

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
    }

}