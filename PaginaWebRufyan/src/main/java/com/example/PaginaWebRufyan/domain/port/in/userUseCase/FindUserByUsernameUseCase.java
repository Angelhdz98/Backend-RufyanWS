package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface FindUserByUsernameUseCase {
    UserDomain findUserByUsername(String username);
}
