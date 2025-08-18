package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.DeleteUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;

import java.util.Optional;

public class DeleteUserByIdService implements
        DeleteUserByIdUseCase {

    private final UserRepository userRepository;

    public DeleteUserByIdService(UserRepository userRepository){
    this.userRepository= userRepository;
    }

    @Override
    public void deleteUserById(Long userId) {
        Optional<UserDomain> optionalUser = userRepository.findUserById(userId);
        if(optionalUser.isEmpty())throw new ResourceNotFoundException("No existe un usuario con ese ID");
       userRepository.deleteUser(optionalUser.get());


    }
}
