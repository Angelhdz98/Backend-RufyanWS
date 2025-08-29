package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByIdUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdService implements FindUserByIdUseCase {

    private final UserRepository userRepository;

    public FindUserByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el id: " + userId));
    }
}
