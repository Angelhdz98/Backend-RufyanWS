package com.example.PaginaWebRufyan.Service.UserServiceAdapter;


import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class IsUsernameAvailableService {
    private final UserRepositoryPort userRepositoryPort;

    public IsUsernameAvailableService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    public Boolean isUsernameAvailable(String username) {

        return !userRepositoryPort.existsByUsername(username);
    }
}
