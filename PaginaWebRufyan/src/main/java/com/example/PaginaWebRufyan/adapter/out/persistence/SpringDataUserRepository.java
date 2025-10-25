package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.User.Entity.UserEntity;
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


    Page<UserEntity> findByUsernameContainingIgnoreCase(String usernamePart, Pageable pageable);
    Page<UserEntity> findByStringFullNameContainingIgnoreCase(String namePart, Pageable pageable);
    Page<UserEntity> findByEmailContainingIgnoreCase(String emailPart, Pageable pageable);

    @Query("SELECT u FROM UserEntity u " +
            "WHERE u.id IN (SELECT l.userId FROM Likes l WHERE l.productId = :productId)")
    Page<UserEntity> findUsersWhoLikedProduct(@Param("productId") Long productId, Pageable pageable);




}
