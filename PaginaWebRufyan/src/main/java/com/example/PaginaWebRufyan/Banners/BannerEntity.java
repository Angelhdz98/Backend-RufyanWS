package com.example.PaginaWebRufyan.Banners;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BannerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long imageId;
    private String bannerName;
    private String goToLink;
    private String imageUrl;
    private String message;
    private Boolean isStatic;

    public BannerEntity(Long id, Long imageId, String bannerName, String goToLink, String imageUrl) {
        this.id = id;
        this.imageId = imageId;
        this.bannerName = bannerName;
        this.goToLink = goToLink;
        this.imageUrl = imageUrl;
        this.isStatic = false;
        this.message =  "";
    }

    // Métodos getter seguros que devuelven valores por defecto si son null
    public String getMessage() {
        return message != null ? message : "";
    }

    public Boolean getIsStatic() {
        return isStatic != null ? isStatic : false;
    }
}
