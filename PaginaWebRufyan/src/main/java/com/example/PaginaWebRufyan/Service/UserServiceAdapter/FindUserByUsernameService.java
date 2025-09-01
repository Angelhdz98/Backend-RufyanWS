package com.example.PaginaWebRufyan.Service.UserServiceAdapter;


import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUserByUsernameUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FindUserByUsernameService implements FindUserByUsernameUseCase {
    private final UserRepository userRepository;


    public FindUserByUsernameService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain findUserByUsername(String username) {
        return userRepository.retrieveUserByUsername(username);
    }
}
