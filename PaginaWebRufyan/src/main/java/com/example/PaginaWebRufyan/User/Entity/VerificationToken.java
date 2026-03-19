package com.example.PaginaWebRufyan.User.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String token;
    @Getter
    private Long userId;
    @Getter
    private LocalDateTime expirationDate;

    public VerificationToken(){}
    public VerificationToken(Long id, String token, Long userId, LocalDateTime expirationDate) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }
}
