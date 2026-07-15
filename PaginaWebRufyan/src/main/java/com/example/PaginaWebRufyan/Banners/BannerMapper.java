package com.example.PaginaWebRufyan.Banners;

import org.springframework.stereotype.Component;

@Component
public class BannerMapper {

    public BannerDTO toDTO(BannerEntity banner) {
        return new BannerDTO(banner.getId(), banner.getImageId(),
                banner.getGoToLink(), banner.getImageUrl(), banner.getBannerName());
    }

    public BannerEntity toEntity(BannerDTO bannerDTO){
        return new BannerEntity(bannerDTO.id(), bannerDTO.imageId(),
                bannerDTO.bannerName(), bannerDTO.goTo(),
                bannerDTO.imageUrl());
    }

}
