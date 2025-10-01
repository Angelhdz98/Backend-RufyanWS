package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.adapter.in.LikeControllers.LikeCommand;
import com.example.PaginaWebRufyan.domain.model.LikeDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface LikesRepositoryPort {
    Set<LikeDomain> findUserLikes(Long userId);
    Page<LikeDomain>findUserLikes(Long userId, Pageable pageable);

    LikeDomain markAsLiked(LikeCommand likeDomain);
    //LikesDomain markAsLiked(Long userId, Long productId);
    boolean existsLike(Long userId, Long productId);
    void unmarkAsLiked(Long userId, Long productId);
    Set<LikeDomain> listUsersLiked(Long productId);
    Page<LikeDomain> listUsersLiked(Long productId, Pageable page);
    Long countLikesFromProduct(Long productId);
    Long countUserLikes(Long userId);




}
