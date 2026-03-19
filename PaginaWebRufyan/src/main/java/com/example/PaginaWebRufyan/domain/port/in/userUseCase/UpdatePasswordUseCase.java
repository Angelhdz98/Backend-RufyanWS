package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

public interface UpdatePasswordUseCase {
    public void updatePassword( Long userId, String oldPassword, String newPassword);
}
