package com.example.PaginaWebRufyan.domain.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LikeDomain {
    private final Long id;
    private final Long userId;
    private final Long productId;


    public LikeDomain(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        }


}

