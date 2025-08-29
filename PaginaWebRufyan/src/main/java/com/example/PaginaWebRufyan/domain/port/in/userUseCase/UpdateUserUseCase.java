package com.example.PaginaWebRufyan.domain.port.in.userUseCase;


import com.example.PaginaWebRufyan.DTO.UpdateUserCommand;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

public interface UpdateUserUseCase {

    UserDomain updateUser(UpdateUserCommand command);

}
