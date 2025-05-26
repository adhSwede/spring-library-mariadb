package dev.jonas.library.services;

import dev.jonas.library.dtos.user.UserDTO;
import dev.jonas.library.dtos.user.UserInputDTO;
import dev.jonas.library.entities.User;
import dev.jonas.library.exceptions.UserNotFoundException;
import dev.jonas.library.mappers.DtoToEntityMapper;
import dev.jonas.library.mappers.EntityToDtoMapper;
import dev.jonas.library.repositories.UserRepository;
import dev.jonas.library.updaters.UserUpdater;
import dev.jonas.library.utils.EntityFetcher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUserDTOs() {
        return userRepository.findAll().stream()
                .map(EntityToDtoMapper::mapToUserDto)
                .toList();
    }

    @Override
    public UserDTO getUserDtoById(Long userId) {
        User user = EntityFetcher.getUserOrThrow(userId, userRepository);
        return EntityToDtoMapper.mapToUserDto(user);
    }

    @Override
    public UserDTO addUser(UserInputDTO dto) {
        User user = DtoToEntityMapper.mapToUserEntity(dto);
        User savedUser = userRepository.save(user);
        return EntityToDtoMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDTO updateUser(Long userId, UserInputDTO updatedUser) {
        User user = EntityFetcher.getUserOrThrow(userId, userRepository);
        UserUpdater.apply(user, updatedUser);
        User savedUser = userRepository.save(user);
        return EntityToDtoMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }
}
