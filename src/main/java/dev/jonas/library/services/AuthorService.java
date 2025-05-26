package dev.jonas.library.services;

import dev.jonas.library.dtos.author.AuthorDTO;
import dev.jonas.library.dtos.author.AuthorInputDTO;

import java.util.List;

public interface AuthorService {
    // GET
    List<AuthorDTO> getAllAuthorDTOs();

    AuthorDTO getAuthorDtoById(Long authorId);

    // POST
    AuthorDTO addAuthor(AuthorInputDTO dto);

    // PUT
    AuthorDTO updateAuthor(Long authorId, AuthorInputDTO dto);

    // DELETE
    void deleteAuthorById(Long authorId);
}
