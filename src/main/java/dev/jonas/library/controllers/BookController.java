package dev.jonas.library.controllers;

import dev.jonas.library.dtos.book.BookDetailsDTO;
import dev.jonas.library.dtos.book.BookInputDTO;
import dev.jonas.library.services.BookService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ########## [ GET ] ##########
    @GetMapping("/books")
    public List<BookDetailsDTO> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDetailsDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/books/search")
    public List<BookDetailsDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, author);
    }

    // ########## [ POST ] ##########
    @PostMapping("/books")
    public BookDetailsDTO addBook(@RequestBody BookInputDTO dto) {
        return bookService.addBook(dto);
    }

}