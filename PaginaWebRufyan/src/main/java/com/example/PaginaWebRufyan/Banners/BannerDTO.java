package com.example.PaginaWebRufyan.Banners;

public record BannerDTO(Long id,
                        Long imageId,
                        String goTo,
                        String imageUrl,
                        String bannerName,
                        String message) {
}