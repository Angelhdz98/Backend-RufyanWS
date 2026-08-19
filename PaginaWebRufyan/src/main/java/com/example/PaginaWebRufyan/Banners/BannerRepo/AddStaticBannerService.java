package com.example.PaginaWebRufyan.Banners.BannerRepo;

import com.example.PaginaWebRufyan.Banners.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AddStaticBannerService {
    private final BannerJPARepository bannerRepository;
    private final AddBannerService addBannerService;
    private final DeleteBannerService deleteBannerService;

    public AddStaticBannerService(BannerJPARepository bannerRepository, AddBannerService addBannerService, DeleteBannerService deleteBannerService) {
        this.bannerRepository = bannerRepository;
        this.addBannerService = addBannerService;
        this.deleteBannerService = deleteBannerService;
    }

    public BannerDTO addStaticBanner(MultipartFile imageFile,
                                     AddBannerDTO addBannerCommand){

        Optional<BannerEntity> firstByIsStaticTrue = bannerRepository.findFirstByIsStaticTrue();
        firstByIsStaticTrue.ifPresent(bannerEntity -> {
            deleteBannerService.deleteBanner(bannerEntity.getId());
        });

        return addBannerService.addBanner(addBannerCommand,
                imageFile, true);

    }
}
