package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdService implements
        DeleteUserByIdUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public DeleteUserByIdService(UserRepositoryPort userRepositoryPort){
    this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void deleteUserById(Long userId) {
        UserDomain userToDelete = userRepositoryPort.retrieveUserById(userId);
        userRepositoryPort.deleteById(userId);


    }
}
