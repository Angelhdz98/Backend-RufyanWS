package com.example.PaginaWebRufyan.Banners;

import com.example.PaginaWebRufyan.Banners.BannerRepo.BannerJPARepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBannerService implements DeleteBannerUseCase {
    private final BannerJPARepository bannerJPARepository;

    public DeleteBannerService(BannerJPARepository bannerJPARepository) {
        this.bannerJPARepository = bannerJPARepository;
    }

    @Override
    public void deleteBanner(Long bannerId) {
        bannerJPARepository.deleteById(bannerId);
    }
}
