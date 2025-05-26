package dev.jonas.library.controllers;

import dev.jonas.library.dtos.author.AuthorDTO;
import dev.jonas.library.dtos.author.AuthorInputDTO;
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

}
