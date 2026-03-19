package com.example.PaginaWebRufyan.Security.Service;

import com.example.PaginaWebRufyan.Security.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByUserEntity_IdAndIsExpiredFalseAndIsRevokedFalse(Long userId);
    Optional<Token>  findByToken(String Token);
}
