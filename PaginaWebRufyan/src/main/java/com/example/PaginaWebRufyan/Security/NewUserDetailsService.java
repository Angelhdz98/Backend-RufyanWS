package com.example.PaginaWebRufyan.Security;

import com.example.PaginaWebRufyan.domain.model.UserDomain;
import com.example.PaginaWebRufyan.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewUserDetailsService implements UserDetailsService {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDomain foundUser = userRepositoryPort.retrieveUserByEmail(email);

        UserDetails user = User.withUsername(foundUser.getEmail()).password(foundUser.getHashedPassword()).authorities(foundUser.getRole().name()).build();


        return user;
    }
}
