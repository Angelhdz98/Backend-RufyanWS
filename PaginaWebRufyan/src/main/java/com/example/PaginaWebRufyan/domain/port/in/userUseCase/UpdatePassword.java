package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

public interface UpdatePassword {
    public void updatePassword( Long userId, String oldPassword, String newPassword);
}
