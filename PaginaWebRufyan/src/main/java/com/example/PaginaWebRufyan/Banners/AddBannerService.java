package com.example.PaginaWebRufyan.Banners;

import com.example.PaginaWebRufyan.Banners.BannerRepo.BannerJPARepository;
import com.example.PaginaWebRufyan.domain.model.ImageProcessor;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Set;

@Service
public class AddBannerService implements AddBannerUseCase {
    private final ImageProcessor imageProcessor;
    private final BannerJPARepository bannerRepo;

    public AddBannerService(ImageProcessor imageProcessor, BannerJPARepository bannerRepo) {
        this.imageProcessor = imageProcessor;
        this.bannerRepo = bannerRepo;
    }

    @Override
    public BannerDTO addBanner(AddBannerDTO addBannerDTO,
                               MultipartFile file,
                               Boolean isStatic) {

        Set<ImageDomain> imageDomains =
                imageProcessor.processImages(List.of(file),
                addBannerDTO.bannerName());

        ImageDomain bannerImage = imageDomains.stream().findFirst().orElse(null);
        if (bannerImage == null) {
            throw new IllegalArgumentException("Failed to process banner image");
        }

        BannerEntity newBanner =
                bannerRepo.save(new BannerEntity(0L,
                        bannerImage.id(), addBannerDTO.bannerName(),
                addBannerDTO.goTo(), bannerImage.url(),
                        addBannerDTO.message(), isStatic));

        return new BannerDTO(newBanner.getId(),bannerImage.id(),
                newBanner.getGoToLink(), bannerImage.url(),
                newBanner.getBannerName(), newBanner.getMessage());
            }

}
