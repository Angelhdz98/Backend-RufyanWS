package com.example.PaginaWebRufyan.adapter.in;

public record GetCommand(Long productId, Long pageNumber, Long pageSize, String sortBy) {
}
