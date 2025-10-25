package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdService implements FindUserByIdUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindUserByIdService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserDomain findUserById(Long userId) {
        return userRepositoryPort.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id: " + userId));
    }
}
