package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.Security.Roles.RoleEnum;
import lombok.Getter;


@Getter
public class RolesUserDomain {

    public RolesUserDomain(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    private final Long id;
    private final Long userId;
    private final Long roleId;

}
