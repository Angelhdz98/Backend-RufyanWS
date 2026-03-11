package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdatePasswordUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UpdateUserPasswordService implements UpdatePasswordUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UpdateUserPasswordService(CurrentUserService currentUserService, UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        UserDomain currentUser = userRepositoryPort.retrieveUserById(userId);
        if(!encoder.matches(oldPassword,currentUser.getHashedPassword()))throw  new IllegalArgumentException("La contraseña no coincide");

userRepositoryPort.updateUserPassword(currentUser.getId(), encoder.encode(newPassword));

    }
}
