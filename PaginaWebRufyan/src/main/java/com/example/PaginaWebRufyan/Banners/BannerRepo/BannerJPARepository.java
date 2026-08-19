package com.example.PaginaWebRufyan.Banners.BannerRepo;

import com.example.PaginaWebRufyan.Banners.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BannerJPARepository extends JpaRepository<BannerEntity, Long> {

    Optional<BannerEntity> findFirstByIsStaticTrue();

}
