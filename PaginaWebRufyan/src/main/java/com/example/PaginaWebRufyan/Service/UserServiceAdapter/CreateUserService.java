package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.Exceptions.AlreadyExistIdenticatorException;
import com.example.PaginaWebRufyan.Exceptions.EmailAlreadyUsedException;
import com.example.PaginaWebRufyan.Service.EmailVerificationServiceAdapter.EmailVerificationService;
import com.example.PaginaWebRufyan.User.Entity.VerificationToken;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.ShoppingCartRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.UserEmailVerifiedRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.VerificationTokenRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService implements CreateUserUseCase {
       private final UserRepositoryPort userRepositoryPort;
       private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       private final ShoppingCartRepositoryPort shoppingCartRepositoryPort;
       private final VerificationTokenRepositoryPort verificationTokenRepositoryPort;
       private final UserEmailVerifiedRepositoryPort userEmailVerifiedRepositoryPort;
       private final EmailVerificationService emailVerificationService;

    public CreateUserService(UserRepositoryPort userRepositoryPort, ShoppingCartRepositoryPort shoppingCartRepositoryPort, VerificationTokenRepositoryPort verificationTokenRepositoryPort, UserEmailVerifiedRepositoryPort userEmailVerifiedRepositoryPort, EmailVerificationService emailVerificationService) {
        this.userRepositoryPort = userRepositoryPort;
        this.shoppingCartRepositoryPort = shoppingCartRepositoryPort;
        this.verificationTokenRepositoryPort = verificationTokenRepositoryPort;
        this.userEmailVerifiedRepositoryPort = userEmailVerifiedRepositoryPort;
        this.emailVerificationService = emailVerificationService;
    }


    @Override
    public UserDomain createUser(CreateUserCommand createUserCommand) {
        if(userRepositoryPort.existsByEmail(createUserCommand.getEmail())) throw  new EmailAlreadyUsedException("An user with the same email "+createUserCommand.getEmail() + " is already registered ");
        if(userRepositoryPort.existsByUsername(createUserCommand.getUsername()))throw new AlreadyExistIdenticatorException("An user with the same username "+createUserCommand.getUsername() + " is already registered ");

        UserDomain newUser = new UserDomain(0L,createUserCommand.getFullName(),new BirthDate(createUserCommand.getBirthDate()),createUserCommand.getUsername(),createUserCommand.getEmail(), passwordEncoder.encode(createUserCommand.getPassword()));
        UserDomain savedUser = userRepositoryPort.saveUser(newUser);
        shoppingCartRepositoryPort.createShoppingCart(savedUser.getId());
        VerificationToken verificationToken = verificationTokenRepositoryPort.createVerificationToken(savedUser.getId());
        userEmailVerifiedRepositoryPort.createVerification(savedUser.getId());
        emailVerificationService.sendVerificationEmail(savedUser.getEmail(),verificationToken.getToken());
        return savedUser;
    }
}
