package com.example.PaginaWebRufyan.Security;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Token {
    public enum TokenType{BEARER}
    @Id
    @GeneratedValue
    public Long id;
    @Column(columnDefinition = "TEXT",unique = true, length = 1000)
    public String token;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    public boolean isRevoked;
    public boolean isExpired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public  UserEntity userEntity;


}
