package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.RolesUserDomain;
import com.example.PaginaWebRufyan.domain.port.in.RolesUserUseCases.AssignRoleCommand;
import com.example.PaginaWebRufyan.domain.port.in.RolesUserUseCases.RevokeRoleCommand;

public interface RolesUserRepository {
    RolesUserDomain assignRole(AssignRoleCommand assignRoleCommand);
    void revokeRole(RevokeRoleCommand revokeRoleCommand);
}
