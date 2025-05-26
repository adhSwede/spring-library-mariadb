package dev.jonas.library.services;

import dev.jonas.library.dtos.book.BookDetailsDTO;
import dev.jonas.library.dtos.book.BookInputDTO;

import java.util.List;

public interface BookService {
    // GET
    List<BookDetailsDTO> getAllBooks();

    BookDetailsDTO getBookById(Long id);

    List<BookDetailsDTO> searchBooks(String title, String author);

    // POST
    BookDetailsDTO addBook(BookInputDTO dto);
}
