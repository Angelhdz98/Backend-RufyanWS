package com.example.PaginaWebRufyan.adapter.out.persistence;

import com.example.PaginaWebRufyan.domain.model.LikesDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SpringDataLikesRepository extends JpaRepository<LikesDomain, Long> {
        Set<LikesDomain> findByUserId(Long userId);
        Set<UserDomain> findByProductId(Long productId);
        Page<LikesDomain> findByUserId(Long userId, Pageable pageable);
        Page<LikesDomain> findByProductId(Long productId, Pageable pageable);

}
