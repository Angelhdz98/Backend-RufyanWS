package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUsersByUsernameMatchUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindUsersByUsernameMatchService implements FindUsersByUsernameMatchUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public FindUsersByUsernameMatchService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Page<UserDomain> findUsersByUsernameMatch(String usernamePart, Pageable pageable) {
        return userRepositoryPort.findUsersByUsernameMatch(usernamePart, pageable);
    }
}
