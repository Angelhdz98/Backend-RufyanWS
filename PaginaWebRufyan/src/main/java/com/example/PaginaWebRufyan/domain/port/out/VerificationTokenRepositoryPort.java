package com.example.PaginaWebRufyan.domain.port.out;


import com.example.PaginaWebRufyan.User.Entity.VerificationToken;

public interface VerificationTokenRepositoryPort {
    VerificationToken createVerificationToken(Long userId);
    VerificationToken getVerificationToken(String token);

}

