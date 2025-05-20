package dev.jonas.library.services;

import dev.jonas.library.dtos.AuthorDTO;
import dev.jonas.library.entities.Author;
import dev.jonas.library.exceptions.AuthorNotFoundException;
import dev.jonas.library.repositories.AuthorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAllAuthorDTOs() {
        return authorRepository.findAll().stream()
                .map(author -> new AuthorDTO(
                        author.getAuthorId(),
                        author.getFirstName(),
                        author.getLastName(),
                        author.getBirthYear(),
                        author.getNationality()
                ))
                .toList();
    }

    @Override
    public AuthorDTO getAuthorDtoById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));

        return new AuthorDTO(
                author.getAuthorId(),
                author.getFirstName(),
                author.getLastName(),
                author.getBirthYear(),
                author.getNationality()
        );
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setFirstName(updatedAuthor.getFirstName());
                    existingAuthor.setLastName(updatedAuthor.getLastName());
                    existingAuthor.setBirthYear(updatedAuthor.getBirthYear());
                    existingAuthor.setNationality(updatedAuthor.getNationality());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new AuthorNotFoundException("Author with ID " + id + " not found"));
    }

    @Override
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
            authorRepository.deleteById(id);
    }
}