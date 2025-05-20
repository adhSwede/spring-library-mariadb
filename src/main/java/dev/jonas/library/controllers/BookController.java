package dev.jonas.library.controllers;

import dev.jonas.library.dtos.BookWithAuthorDTO;
import dev.jonas.library.entities.Book;
import dev.jonas.library.services.BookService;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ########## [ GET ] ##########
    @GetMapping("/books")
    public List<BookWithAuthorDTO> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookWithAuthorDTO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // ########## [ POST ] ##########
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // ########## [ PUT ] ##########
    @PutMapping("books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    // ########## [ DELETE ] ##########
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }

}