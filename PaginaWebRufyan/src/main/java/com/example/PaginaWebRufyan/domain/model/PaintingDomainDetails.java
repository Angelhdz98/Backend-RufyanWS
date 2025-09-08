package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.MediumEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.SupportMaterialEnum;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class PaintingDomainDetails extends ProductDomainDetails {
    public final static Integer MIN_HEIGHT_CM =10;
    public final static Integer MIN_LARGE_CM =10;



    private final Integer alturaCm;
    private final Integer largoCm;
    private final MediumEnum medium;
    private final SupportMaterialEnum supportMaterial;
    private final LocalDate creationDate;

    public PaintingDomainDetails(Integer alturaCm, Integer largoCm, MediumEnum medium, SupportMaterialEnum supportMaterial, LocalDate creationDate) {
        this.alturaCm = alturaCm;
        this.largoCm = largoCm;
        this.medium = medium;
        this.supportMaterial = supportMaterial;
        this.creationDate = creationDate;
    }


    public PaintingDomainDetails() {

        this.alturaCm = MIN_HEIGHT_CM;
        this.largoCm = MIN_LARGE_CM;
        this.medium = MediumEnum.OIL_PAINT;
        this.supportMaterial = SupportMaterialEnum.COTTON_PAPER;
        this.creationDate = LocalDate.now();

    }

    @Override
    public void validate() {


        if(alturaCm<MIN_HEIGHT_CM || largoCm < MIN_LARGE_CM)throw new IllegalArgumentException("se debe cumplir con altura minima de: "+MIN_HEIGHT_CM+" y un largo minimo de: "+ MIN_LARGE_CM);
        if(creationDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("la fecha creacion no puede ser del futuro");
    }
}
