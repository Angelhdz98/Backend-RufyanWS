package com.example.PaginaWebRufyan.Banners;

import com.example.PaginaWebRufyan.Banners.BannerRepo.AddStaticBannerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AddStaticBannerController {
    private final AddStaticBannerService addStaticBannerService;

    public AddStaticBannerController(AddStaticBannerService addStaticBannerService) {
        this.addStaticBannerService = addStaticBannerService;
    }


    @PostMapping(value= "/banners/static",consumes =
            MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BannerDTO> addFavoriteBanner(@RequestPart("imageFile") MultipartFile imageFile, @RequestPart("addBannerCommand") AddBannerDTO addBannerCommand){

     return ResponseEntity.ok(addStaticBannerService.addStaticBanner(imageFile,addBannerCommand));
    }
}
