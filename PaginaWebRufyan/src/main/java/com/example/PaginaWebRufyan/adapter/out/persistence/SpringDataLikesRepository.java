package com.example.PaginaWebRufyan.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface SpringDataLikesRepository extends JpaRepository<LikeEntity, Long> {
        Set<LikeEntity> findByUserId(Long userId);
        Set<LikeEntity> findByProductId(Long productId);
        Page<LikeEntity> findByUserId(Long userId, Pageable pageable);
        Page<LikeEntity> findByProductId(Long productId, Pageable pageable);

        @Query("SELECT COUNT(l) FROM Likes l  WHERE l.productId = :productId")
        long countLikesByProductId(@Param("productId") Long productId);

        @Query("SELECT COUNT(l) FROM Likes l WHERE l.userId = :userId")
        long countLikesByUserId(@Param("userId") Long userId);



}
