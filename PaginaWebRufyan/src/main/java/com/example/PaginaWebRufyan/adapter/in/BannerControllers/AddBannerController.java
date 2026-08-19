package com.example.PaginaWebRufyan.adapter.in.BannerControllers;

import com.example.PaginaWebRufyan.Banners.AddBannerDTO;
import com.example.PaginaWebRufyan.Banners.AddBannerService;
import com.example.PaginaWebRufyan.Banners.BannerDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class AddBannerController {
    private final AddBannerService addBannerService;

    public AddBannerController(AddBannerService addBannerService) {
        this.addBannerService = addBannerService;
    }

    @PostMapping(value= "/banners",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BannerDTO> addBanner(@RequestPart("imageFile") MultipartFile imageFile, @RequestPart("addBannerCommand") AddBannerDTO addBannerCommand) {

        return ResponseEntity.ok(addBannerService.addBanner(addBannerCommand, imageFile, false));
    }

}
