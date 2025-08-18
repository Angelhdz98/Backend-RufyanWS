package com.example.PaginaWebRufyan.domain.port.in.RolesUserUseCases;

import com.example.PaginaWebRufyan.domain.model.RolesUserDomain;

public interface AssignRoleToUserUseCase {

    RolesUserDomain assignRole(AssignRoleCommand assignRoleCommand);
}

