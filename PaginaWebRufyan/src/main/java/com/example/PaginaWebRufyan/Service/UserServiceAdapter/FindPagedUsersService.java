package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindPagedUsersUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindPagedUsersService implements FindPagedUsersUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindPagedUsersService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Page<UserDomain> findPagedUsers(Pageable pageable) {
        return userRepositoryPort.findUsersPaged(pageable);
    }
}
