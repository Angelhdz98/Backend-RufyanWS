package com.example.PaginaWebRufyan.ControllerTests;

import com.example.PaginaWebRufyan.DTO.CreateUserCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.BirthDate;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.CreateUserCommandMapper;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.FullName;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.CreateUserUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Profile("test")
@Component
public class UserDataInitializer implements CommandLineRunner {
    @Value("${spring.datasource.user-buyer-password}")
    private String userBuyerPassword;
    @Value("${spring.datasource.user-buyer-email}")
    private String userBuyerEmail;
    private final UserRepositoryPort userRepositoryPort;
    private final CreateUserUseCase createUserUseCase;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserCommandMapper createUserCommandMapper;

    public UserDataInitializer(UserRepositoryPort userRepositoryPort, CreateUserUseCase createUserUseCase, CreateUserCommandMapper createUserCommandMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.createUserUseCase = createUserUseCase;
        this.createUserCommandMapper = createUserCommandMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        UserDomain userBuyer = new UserDomain(2L, new FullName("Joel", "Alonso", "Martinez", "Pelaez"), new BirthDate(LocalDate.of(1987,03,28)),"PelaMa", userBuyerEmail, passwordEncoder.encode(userBuyerPassword));
        // Creating the user just by repository jumps the shopping cart creation;
        //userRepositoryPort.saveUser(userBuyer);
        // Using Service instead;
        createUserUseCase.createUser(new CreateUserCommand(userBuyerEmail,userBuyerPassword,userBuyer.getUsername(),userBuyer.getFullname(),userBuyer.getBirthDate().getBirthDate()));

    }
}
