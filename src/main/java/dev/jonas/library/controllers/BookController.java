package dev.jonas.library.controllers;

import dev.jonas.library.dtos.BookDetailsDTO;
import dev.jonas.library.dtos.BookInputDTO;
import dev.jonas.library.entities.Book;
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

    // ########## [ POST ] ##########
    @PostMapping("/books")
    public BookDetailsDTO addBook(@RequestBody BookInputDTO dto) {
        return bookService.addBook(dto);
    }

    // ########## [ PUT ] ##########
    @PutMapping("books/{id}")
    public ResponseEntity<BookDetailsDTO> updateBook(@PathVariable Long id, @RequestBody BookInputDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    // ########## [ DELETE ] ##########
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}