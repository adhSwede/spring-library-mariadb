package dev.jonas.library.services;

import dev.jonas.library.dtos.BookDetailsDTO;
import dev.jonas.library.dtos.BookInputDTO;

import java.util.List;

public interface BookService {
    // GET
    List<BookDetailsDTO> getAllBooks();
    BookDetailsDTO getBookById(Long id);

    // POST
    BookDetailsDTO addBook(BookInputDTO dto);

    // PUT
    BookDetailsDTO updateBook(Long id, BookInputDTO dto);

    // DELETE
    void deleteBookById(Long id);
}
