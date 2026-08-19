package com.example.PaginaWebRufyan.Banners;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStaticBannerController {
    private final GetStaticBannerService getStaticBannerService;

    public GetStaticBannerController(GetStaticBannerService getStaticBannerService) {
        this.getStaticBannerService = getStaticBannerService;
    }

    @GetMapping("/banners/static")
    public ResponseEntity<BannerDTO> getStaticBanner(){
        return ResponseEntity.ok(getStaticBannerService.getStaticBanner());
    }
}
