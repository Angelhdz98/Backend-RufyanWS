package com.example.PaginaWebRufyan.Banners;

import com.example.PaginaWebRufyan.Banners.BannerRepo.BannerJPARepository;
import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetStaticBannerService {
    private final BannerJPARepository bannerJPARepository;
    private final BannerMapper bannerMapper;

    public GetStaticBannerService(BannerJPARepository bannerJPARepository, BannerMapper bannerMapper) {
        this.bannerJPARepository = bannerJPARepository;
        this.bannerMapper = bannerMapper;
    }

    public BannerDTO getStaticBanner(){
        BannerEntity bannerEntity = bannerJPARepository.findAll()
                .stream()
                .filter(BannerEntity::getIsStatic)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No hay banner statico definido"));

        return bannerMapper.toDTO(bannerEntity);
    }
}
