package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.stereotype.Component;


@Component
public class CreateUserCommandMapper {


    public CreateUserCommandMapper() {
    }


    public UserDomain toDomain(CreateUserCommand command){
        // It does not have a ID while is creating
        return new UserDomain(0L, command.getFullName(),new BirthDate(command.getBirthDate()) , command.getUsername(), command.getEmail(), command.getPassword());
    }
    public CreateUserCommand toCommand(UserDomain userDomain){
        return new CreateUserCommand(userDomain.getEmail(),
                userDomain.getHashedPassword(), userDomain.getUsername(), userDomain.getFullname(), userDomain.getBirthDate().getBirthDate());
    }

}
