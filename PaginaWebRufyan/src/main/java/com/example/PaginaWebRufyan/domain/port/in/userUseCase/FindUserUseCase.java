package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import org.apache.catalina.User;

import java.util.Optional;

public interface FindUserUseCase {
    Optional<User> findUserById(Integer userId);
}
