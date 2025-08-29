package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DeleteUserByIdService implements
        DeleteUserByIdUseCase {

    private final UserRepository userRepository;

    public DeleteUserByIdService(UserRepository userRepository){
    this.userRepository= userRepository;
    }

    @Override
    public void deleteUserById(Long userId) {
        UserDomain userToDelete = userRepository.retrieveUserById(userId);
        userRepository.deleteById(userId);


    }
}
