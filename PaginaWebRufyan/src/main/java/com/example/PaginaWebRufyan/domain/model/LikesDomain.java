package com.example.PaginaWebRufyan.domain.model;

import lombok.Getter;

@Getter
public class LikesDomain {
    private final Long id;
    private final Long userId;
    private final Long productId;


    public LikesDomain(Long userId, Long productId, Long id) {
        this.id=id;
        this.userId = userId;
        this.productId = productId;

    }

}

