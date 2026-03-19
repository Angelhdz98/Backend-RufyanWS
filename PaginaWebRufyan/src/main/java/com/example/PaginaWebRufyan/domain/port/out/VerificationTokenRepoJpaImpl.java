package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.User.Entity.VerificationToken;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class VerificationTokenRepoJpaImpl implements VerificationTokenRepositoryPort{
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenRepoJpaImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public VerificationToken retrieveVerificationToken (String token){
        return verificationTokenRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("No se encontró el token"));
    }




    @Override
    public VerificationToken createVerificationToken(Long userId) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken= new VerificationToken(0L,token,userId, LocalDateTime.now().plusHours(24));

        return verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return retrieveVerificationToken(token);
    }
}
