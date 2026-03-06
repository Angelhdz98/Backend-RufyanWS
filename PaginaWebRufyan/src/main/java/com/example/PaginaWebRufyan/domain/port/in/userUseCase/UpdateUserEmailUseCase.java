package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface UpdateUserEmailUseCase {
     UserDomain updateEmail(Long userId, String userEmail);
}
