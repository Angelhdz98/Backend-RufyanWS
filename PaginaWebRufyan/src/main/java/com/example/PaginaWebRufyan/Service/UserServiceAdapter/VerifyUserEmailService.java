package com.example.PaginaWebRufyan.Service.UserServiceAdapter;

import com.example.PaginaWebRufyan.Exceptions.InvalidTokenException;
import com.example.PaginaWebRufyan.User.Entity.VerificationToken;
import com.example.PaginaWebRufyan.domain.port.in.userUseCase.VerifyUserEmailUseCase;
import com.example.PaginaWebRufyan.domain.port.out.UserEmailVerifiedRepositoryPort;
import com.example.PaginaWebRufyan.domain.port.out.VerificationTokenRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerifyUserEmailService implements VerifyUserEmailUseCase {
    /*To verifyUser first find verificationTokenByToken
    then change the userEmail Verified with UserEmailVerifiedRepositoryPort
     */
    private final VerificationTokenRepositoryPort verificationTokenRepositoryPort;
    private final UserEmailVerifiedRepositoryPort userEmailVerifiedRepositoryPort;

    public VerifyUserEmailService(VerificationTokenRepositoryPort verificationTokenRepositoryPort, UserEmailVerifiedRepositoryPort userEmailVerifiedRepositoryPort) {
        this.verificationTokenRepositoryPort = verificationTokenRepositoryPort;
        this.userEmailVerifiedRepositoryPort = userEmailVerifiedRepositoryPort;
    }

    @Override
    public String verifyEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepositoryPort.getVerificationToken(token);
        if(verificationToken.getExpirationDate().isBefore(LocalDateTime.now())) throw new InvalidTokenException("Email no verificado token expirado");
        userEmailVerifiedRepositoryPort.setEmailToVerified(verificationToken.getUserId());
        return "Token Verificado";
    }
}
