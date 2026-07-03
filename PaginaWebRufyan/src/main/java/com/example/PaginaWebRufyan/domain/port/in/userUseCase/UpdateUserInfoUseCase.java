package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.DTO.UpdateUserInfoCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface UpdateUserInfoUseCase {
    UserDomain updateUserInfo(UpdateUserInfoCommand command);
}
