package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Page<UserEntity>  findByUsernameContaining(String usernamePart, Pageable pageable);

    @Query("SELECT u FROM UserEntity u " +
            "JOIN LikeEntity l ON u.id = l.userId " +
            "WHERE l.painting.id = :paintingId")
    Page<UserEntity> findUsersWhoLikedPainting(@Param("paintingId") Long paintingId, Pageable pageable);
}
