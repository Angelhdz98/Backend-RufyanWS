package com.example.PaginaWebRufyan.Banners;

import com.example.PaginaWebRufyan.Banners.BannerRepo.BannerJPARepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetBannersService {
    private final BannerJPARepository bannerJPARepository ;
    private final BannerMapper bannerMapper;

    public GetBannersService(BannerJPARepository bannerJPARepository, BannerMapper bannerMapper) {
        this.bannerJPARepository = bannerJPARepository;
        this.bannerMapper = bannerMapper;
    }
    public ArrayList<BannerDTO> getBanners(){

        List<BannerEntity> allBanners = bannerJPARepository.findAll();
        // Filtrar solo los banners que NO sean estáticos
        List<BannerDTO> bannerDTOList = allBanners.stream()
                .filter(banner -> !banner.getIsStatic())
                .map(bannerMapper::toDTO)
                .toList();

        return  new ArrayList<>(bannerDTOList);
    }
}
