package com.example.PaginaWebRufyan.Banners;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GetBannersController {
private final GetBannersService getBannersService;

    public GetBannersController(GetBannersService getBannersService) {
        this.getBannersService = getBannersService;
    }
    @GetMapping("/banners")
    public ResponseEntity<ArrayList<BannerDTO>> getBanners() {
        ArrayList<BannerDTO> banners = getBannersService.getBanners();
        return ResponseEntity.ok(banners);
    }

}
