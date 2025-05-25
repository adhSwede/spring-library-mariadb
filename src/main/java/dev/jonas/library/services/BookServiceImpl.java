package dev.jonas.library.services;

import dev.jonas.library.dtos.BookDetailsDTO;
import dev.jonas.library.dtos.BookInputDTO;

import dev.jonas.library.entities.Author;
import dev.jonas.library.entities.Book;

import dev.jonas.library.exceptions.BookNotFoundException;

import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;

import dev.jonas.library.repositories.AuthorRepository;
import dev.jonas.library.repositories.BookRepository;

import dev.jonas.library.utils.EntityFetcher;
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
    public BookDetailsDTO addBook(BookInputDTO dto) {
        Author author = EntityFetcher.getAuthorOrThrow(dto.getAuthorId(), authorRepository);

        Book book = DtoToEntityMapper.mapToBookEntity(dto, author);
        Book savedBook = bookRepository.save(book);

        return EntityToDtoMapper.mapToBookDetailsDto(savedBook);
    }

    @Override
    public BookDetailsDTO updateBook(Long bookId, BookInputDTO dto) {
        Author author = EntityFetcher.getAuthorOrThrow(dto.getAuthorId(), authorRepository);
        Book updatedBook = EntityFetcher.getBookOrThrow(bookId, bookRepository);

        updatedBook.setTitle(dto.getTitle());
        updatedBook.setAuthor(author);
        updatedBook.setPublicationYear(dto.getPublicationYear());
        updatedBook.setAvailableCopies(dto.getAvailableCopies());
        updatedBook.setTotalCopies(dto.getTotalCopies());

        Book savedBook = bookRepository.save(updatedBook);
        return EntityToDtoMapper.mapToBookDetailsDto(savedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    public void decrementAvailableCopies(Long bookId){
        Book book = EntityFetcher.getBookOrThrow(bookId, bookRepository);

        if  (book.getAvailableCopies() <= 0){
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