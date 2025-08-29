package com.example.PaginaWebRufyan.Security.adapter;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.UserValidators;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@Entity
public class SecurityUserDetails  {//implements UserDetails
    private final Integer userId;
    private final String password;
    private final String username;
    private final String email;
    private final UserValidators userValidators;
    private final Set<Integer> rolesId;
    private final Set<Integer> permissionsId;




  /* // implementaciones incompletas de lo metodos de userDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

   */

}
