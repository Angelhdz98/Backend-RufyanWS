package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override
    public UserDomain updateUser(UpdateUserCommand command) {
        UserDomain userToUpdate = userRepository.retrieveUserById(command.getUserId());

        if (!userToUpdate.getEmail().equals(
                command.getEmail())) {
            if (userRepository.existsByEmail(command.getEmail())) {
                throw new EmailAlreadyUsedException("El email ya esta siendo utilizado");
            }
        }

        if (!userToUpdate.getUsername().equals(command.getUsername())) {
            if (userRepository.existsByUsername(command.getUsername())) {
                throw new AlreadyExistIdenticatorException("Ya existe un usuario registrado con el mismo username: " + command.getUsername());
            }
        }
        UserDomain updatedUser = new UserDomain(command.getUserId(),command.getFullName(),new BirthDate(command.getBirthDate()), command.getEmail(), command.getUsername());

     return userRepository.saveUser(updatedUser);
    }
}
