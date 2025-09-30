package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUsersByEmailMatchUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindUsersByEmailContainingService implements FindUsersByEmailMatchUseCase {

   private final UserRepositoryPort userRepositoryPort;

    public FindUsersByEmailContainingService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Page<UserDomain> findUsersByEmailMatch(String emailPart, Pageable pageable) {
        return userRepositoryPort.findUsersByEmailMatch(emailPart, pageable);

    }
}
