package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.MediumEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.SupportMaterialEnum;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class PaintingDomainDetails extends ProductDomainDetails {
    private final Integer alturaCm;
    private final Integer largoCm;
    private final MediumEnum medium;
    private final SupportMaterialEnum supportMaterial;
    private final LocalDate creationDate;
    private final ProductDomainDetails productDetails;

    public PaintingDomainDetails(Integer alturaCm, Integer largoCm, MediumEnum medium, SupportMaterialEnum supportMaterial, LocalDate creationDate, ProductDomainDetails productDetails) {

        this.alturaCm = alturaCm;
        this.largoCm = largoCm;
        this.medium = medium;
        this.supportMaterial = supportMaterial;
        this.creationDate = creationDate;
        this.productDetails = productDetails;
    }

    @Override
    public void validate() {
        int alturaMinimaCm = 10;
        int largoMinimoCm =10;
        if(alturaCm<alturaMinimaCm || largoCm < largoMinimoCm)throw new IllegalArgumentException("se debe cumplir con altura minima de: "+alturaMinimaCm+" y un largo minimo de: "+ largoMinimoCm);
        if(creationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("la fecha creacion no puede ser del futuro");
    }
}
