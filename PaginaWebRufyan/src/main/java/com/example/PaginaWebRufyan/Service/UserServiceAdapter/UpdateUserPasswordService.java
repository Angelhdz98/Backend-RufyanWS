package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdatePassword;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UpdateUserPasswordService implements UpdatePassword {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UpdateUserPasswordService(CurrentUserService currentUserService, UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        UserDomain currentUser = userRepositoryPort.retrieveUserById(userId);
        if(!encoder.matches(oldPassword,currentUser.getHashedPassword()))throw  new IllegalArgumentException("La contraseña no coincide");

        userRepositoryPort.updateUser(new UserDomain(currentUser.getId(),
                currentUser.getFullname(),
                currentUser.getBirthDate(),
                currentUser.getUsername(),
                currentUser.getEmail(),
                encoder.encode(newPassword)));

    }
}
