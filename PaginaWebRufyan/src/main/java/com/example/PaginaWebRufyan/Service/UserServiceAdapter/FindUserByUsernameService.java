package com.example.PaginaWebRufyan.Service.UserServiceAdapter;


import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByUsernameUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class FindUserByUsernameService implements FindUserByUsernameUseCase {
    private final UserRepositoryPort userRepositoryPort;


    public FindUserByUsernameService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain findUserByUsername(String username) {
        return userRepositoryPort.retrieveUserByUsername(username);
    }
}
