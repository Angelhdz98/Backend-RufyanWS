package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {
private final UserRepository userRepository;


    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain createUser(CreateUserCommand createUserCommand) {
         if(userRepository.existsByEmail(createUserCommand.getEmail())) throw  new EmailAlreadyUsedException("An user with the same email "+createUserCommand.getEmail() + " is already registered ");
         if(userRepository.existsByUsername(createUserCommand.getUsername()))throw new UsernameAlreadyUsedException("An user with the same username "+createUserCommand.getUsername() + " is already registered ");

         UserDomain newUser = new UserDomain(createUserCommand.getFullName(),new BirthDate(createUserCommand.getBirthDate()),createUserCommand.getEmail(),createUserCommand.getUsername());
         return userRepository.saveUser(newUser);

    }
}
