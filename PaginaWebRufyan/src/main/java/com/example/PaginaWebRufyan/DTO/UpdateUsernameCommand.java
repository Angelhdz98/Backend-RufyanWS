package com.example.PaginaWebRufyan.DTO;

public record UpdateUsernameCommand(Long userId, String newUsername
        , String password) {
}
