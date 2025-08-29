package com.example.PaginaWebRufyan.domain.model;

import lombok.Getter;

@Getter
public class OriginalOwnersDomain {
    private final Long userId;
    private final Long paintingId;


    public OriginalOwnersDomain(Long userId, Long paintingId) {
        this.userId = userId;
        this.paintingId = paintingId;
    }
}
