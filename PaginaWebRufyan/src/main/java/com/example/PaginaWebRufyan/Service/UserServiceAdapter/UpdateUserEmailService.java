package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.UpdateUserEmailUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserEmailService implements UpdateUserEmailUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public UpdateUserEmailService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain updateEmail(Long userId, String userEmail) {
        UserDomain currentUser = userRepositoryPort.retrieveUserById(userId);
        return userRepositoryPort.updateUserEmail(userId,userEmail);
    }
}
