package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserService(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain updateUser(UpdateUserCommand command) {
        UserDomain userToUpdate = userRepositoryPort.retrieveUserById(command.getUserId());


        if (!userToUpdate.getEmail().equals(
                command.getEmail())) {
            if (userRepositoryPort.existsByEmail(command.getEmail())) {
                throw new EmailAlreadyUsedException("El email ya esta siendo utilizado");
            }
        }

        if (!userToUpdate.getUsername().equals(command.getUsername())) {
            if (userRepositoryPort.existsByUsername(command.getUsername())) {
                throw new AlreadyExistIdenticatorException("Ya existe un usuario registrado con el mismo username: " + command.getUsername());
            }
        }
        BirthDate birthDate = new BirthDate(command.getBirthDate());
        if(!birthDate.isAdult()) throw new IllegalArgumentException("Prohibido el registro a menores");
        UserDomain updatedUser = new UserDomain(command.getUserId(),command.getFullName(),new BirthDate(command.getBirthDate()), command.getUsername(), command.getEmail());

     return userRepositoryPort.saveUser(updatedUser);
    }
}
