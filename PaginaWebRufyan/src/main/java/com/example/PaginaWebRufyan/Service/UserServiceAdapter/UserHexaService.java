package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.*;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;

import java.util.Optional;


//@Service
public class UserHexaService implements CreateUserUseCase, DeleteUserByIdUseCase, FindUserByIdUseCase, UpdateUserUseCase, FindUserByUsernameUseCase {
private final UserRepositoryPort userRepositoryPort;


    public UserHexaService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }



    @Override
    public UserDomain createUser(CreateUserCommand createUserCommand) {
        if(userRepositoryPort.existsByEmail(createUserCommand.getEmail())) throw  new EmailAlreadyUsedException("An user with the same email "+createUserCommand.getEmail() + " is already registered ");
        if(userRepositoryPort.existsByUsername(createUserCommand.getUsername()))throw new UsernameAlreadyUsedException("An user with the same username "+createUserCommand.getUsername() + " is already registered ");

        UserDomain newUser = new UserDomain(0L,createUserCommand.getFullName(),new BirthDate(createUserCommand.getBirthDate()),createUserCommand.getEmail(),createUserCommand.getUsername());
        return userRepositoryPort.saveUser(newUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepositoryPort.deleteById(userId);
    }



    @Override
    public UserDomain updateUser(UpdateUserCommand command) {
        Optional<UserDomain> foundUser = userRepositoryPort
                .findUserById(command.getUserId());
        if(foundUser.isEmpty()) throw new ResourceNotFoundException("no se encontró el usuario con el id: "+ command.getUserId());
        if( !foundUser.get().getUsername().equals(command.getUsername()) && userRepositoryPort.existsByUsername(command.getUsername())) throw new UsernameAlreadyUsedException("el username: "+ command.getUsername()+" ya lo esta usando un usuario " );

        if(!foundUser.get().getEmail().equals(command.getEmail()) && userRepositoryPort.existsByEmail(command.getEmail()) )
            throw new EmailAlreadyUsedException("Ya existe una cuenta registrada con el email: "+ command.getEmail());

        UserDomain updatedUser = new UserDomain(command.getUserId(), command.getFullName(),new BirthDate(command.getBirthDate()),command.getUsername(),command.getEmail());
       return userRepositoryPort.saveUser(updatedUser);
    }

    @Override
    public UserDomain findUserById(Long userId) {
        return userRepositoryPort.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el usuario con el id: " + userId
                ));
    }
    @Override
    public UserDomain findUserByUsername(String username) {
       return userRepositoryPort.findUserByUsername(username).orElseThrow(()-> new ResourceNotFoundException("No existe el usuario con el nombre: "));
    }
}
