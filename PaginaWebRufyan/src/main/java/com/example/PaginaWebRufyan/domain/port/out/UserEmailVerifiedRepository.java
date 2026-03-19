package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.User.Entity.UserEmailVerifiedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEmailVerifiedRepository extends JpaRepository<UserEmailVerifiedEntity, Long> {
    Optional<UserEmailVerifiedEntity> findByUserId(Long userId);
}
