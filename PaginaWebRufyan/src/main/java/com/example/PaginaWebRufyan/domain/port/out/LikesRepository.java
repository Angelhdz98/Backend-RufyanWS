package com.example.PaginaWebRufyan.domain.port.out;

import com.example.PaginaWebRufyan.domain.model.LikesDomain;
import com.example.PaginaWebRufyan.domain.model.UserDomain;

import java.util.Set;

public interface LikesRepository {
    Set<LikesDomain> findUserLikes(Long userId);
    LikesDomain markAsLiked(LikesDomain likesDomain);
    //LikesDomain markAsLiked(Long userId, Long productId);
    boolean existsLike(Long userId, Long productId);
    void unmarkAsLiked(Long userId, Long productId);
    Set<UserDomain> listUsersLiked(Long productId);
    Long countLikesFromProduct(Long productId);
    Long countUserLikes(Long userId);




}
