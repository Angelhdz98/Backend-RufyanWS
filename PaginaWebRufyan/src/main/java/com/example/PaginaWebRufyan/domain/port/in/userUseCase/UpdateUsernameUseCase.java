package com.example.PaginaWebRufyan.domain.port.in.userUseCase;

import com.example.PaginaWebRufyan.DTO.UpdateUsernameCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface UpdateUsernameUseCase {

    UserDomain updateUsername(UpdateUsernameCommand updateUserCommand);
}
