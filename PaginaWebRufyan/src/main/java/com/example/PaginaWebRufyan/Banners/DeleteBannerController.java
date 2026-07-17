package com.example.PaginaWebRufyan.Banners;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteBannerController {

    private final DeleteBannerUseCase deleteBannerUseCase;


    public DeleteBannerController(DeleteBannerUseCase deleteBannerUseCase) {
        this.deleteBannerUseCase = deleteBannerUseCase;
    }

    @DeleteMapping("/banners/{bannerId}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long bannerId) {
        deleteBannerUseCase.deleteBanner(bannerId);
        return ResponseEntity.ok().build();
    }
}
