package com.example.PaginaWebRufyan.Banners;

import org.springframework.web.multipart.MultipartFile;


public interface AddBannerUseCase {
   BannerDTO addBanner(
                       AddBannerDTO addBannerDTO,
                       MultipartFile imageFile);
}
