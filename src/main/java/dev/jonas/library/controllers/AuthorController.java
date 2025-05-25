package dev.jonas.library.controllers;

import dev.jonas.library.dtos.AuthorDTO;
import dev.jonas.library.dtos.AuthorInputDTO;
import dev.jonas.library.services.AuthorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // ########## [ GET ] ##########
    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthorDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorDtoById(id));
    }

    // ########## [ POST ] ##########
    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorInputDTO dto) {
        return ResponseEntity.ok(authorService.addAuthor(dto));
    }

    // ########## [ PUT ] ##########
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorInputDTO dto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, dto));
    }

    // ########## [ DELETE ] ##########
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
