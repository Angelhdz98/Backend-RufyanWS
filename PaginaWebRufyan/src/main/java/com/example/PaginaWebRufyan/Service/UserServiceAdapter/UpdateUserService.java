package com.example.PaginaWebRufyan.Service.UserServiceAdapter;


import com.example.PaginaWebRufyan.DTO.UpdateUserInfoCommand;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

//It does not change password and email just verify the password
@Service
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final CurrentUserService currentUserService;

    public UpdateUserService(UserRepositoryPort userRepositoryPort, CurrentUserService currentUserService){
        this.userRepositoryPort = userRepositoryPort;

        this.currentUserService = currentUserService;
    }

    @Override
    public UserDomain updateUser(UpdateUserInfoCommand command)  {
        UserDomain userToUpdate = currentUserService.getCurrentUser();


        //Identification update data will be changed for a specifics services and controllers
     /*   if (!userToUpdate.getEmail().equals(
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
        */

        BirthDate birthDate = new BirthDate(command.birthDate());
        if(!birthDate.isAdult()) throw new IllegalArgumentException("Prohibido el registro a menores");
        UserDomain updatedUser =
                new UserDomain(userToUpdate.getId(),
                command.fullName(),new BirthDate(command.birthDate()),
                userToUpdate.getUsername(), userToUpdate.getEmail(),
                userToUpdate.getHashedPassword(), userToUpdate.getRole());

     return userRepositoryPort.saveUser(updatedUser);
    }
}
