package dev.jonas.library.services;

import dev.jonas.library.dtos.author.AuthorDTO;
import dev.jonas.library.dtos.author.AuthorInputDTO;
import dev.jonas.library.entities.Author;
import dev.jonas.library.exceptions.AuthorNotFoundException;
import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;
import dev.jonas.library.repositories.AuthorRepository;

import dev.jonas.library.utils.EntityFetcher;
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
                .map(EntityToDtoMapper::mapToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDTO getAuthorDtoById(Long authorId) {
        Author author = EntityFetcher.getAuthorOrThrow(authorId, authorRepository);

        return EntityToDtoMapper.mapToAuthorDto(author);
    }

    @Override
    public AuthorDTO addAuthor(AuthorInputDTO dto) {
        Author author = DtoToEntityMapper.mapToAuthorEntity(dto);
        Author savedAuthor = authorRepository.save(author);
        return EntityToDtoMapper.mapToAuthorDto(savedAuthor);
    }

    @Override
    public AuthorDTO updateAuthor(Long authorId, AuthorInputDTO dto) {
        Author updatedAuthor = EntityFetcher.getAuthorOrThrow(authorId, authorRepository);

        updatedAuthor.setFirstName(dto.getFirstName());
        updatedAuthor.setLastName(dto.getLastName());
        updatedAuthor.setBirthYear(dto.getBirthYear());
        updatedAuthor.setNationality(dto.getNationality());

        Author savedAuthor = authorRepository.save(updatedAuthor);
        return EntityToDtoMapper.mapToAuthorDto(savedAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found");
        }
            authorRepository.deleteById(id);
    }
}