package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserDomain, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
