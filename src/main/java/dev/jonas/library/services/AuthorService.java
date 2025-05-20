package dev.jonas.library.services;

import dev.jonas.library.dtos.AuthorDTO;
import dev.jonas.library.entities.Author;

import java.util.List;

public interface AuthorService {
    // GET
    List<AuthorDTO> getAllAuthorDTOs();
    AuthorDTO getAuthorDtoById(Long id);

    // POST
    Author addAuthor(Author author);

    // PUT
    Author updateAuthor(Long id, Author author);

    // DELETE
    void deleteAuthorById(Long id);
}
