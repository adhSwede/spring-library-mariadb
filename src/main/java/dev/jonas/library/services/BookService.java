package dev.jonas.library.services;

import dev.jonas.library.dtos.BookWithAuthorDTO;
import dev.jonas.library.entities.Book;

import java.util.List;

public interface BookService {
    // GET
    List<BookWithAuthorDTO> getAllBooks();
    BookWithAuthorDTO getBookById(Long id);

    // POST
    Book addBook(Book book);

    // PUT
    Book updateBook(Long id, Book book);

    // DELETE
    void deleteBookById(Long id);
}
