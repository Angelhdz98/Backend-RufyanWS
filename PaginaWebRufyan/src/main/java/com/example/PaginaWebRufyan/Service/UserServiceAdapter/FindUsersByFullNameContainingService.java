package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.FindUsersByFullNameMatchUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindUsersByFullNameContainingService implements FindUsersByFullNameMatchUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public FindUsersByFullNameContainingService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }


    @Override
    public Page<UserDomain> findUsersByFullNameMatch(String namePart, Pageable pageable) {
        return userRepositoryPort.findUsersByNameMatch(namePart,pageable);
    }
}
