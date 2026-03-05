package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final UserRepositoryPort userRepositoryPort;

    public CurrentUserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }
    public UserDomain getCurrentUser(){
        Authentication authentication= SecurityContextHolder.getContext()
                .getAuthentication();
        String email = authentication.getName();
        return userRepositoryPort.retrieveUserByEmail(email);
    }
}
