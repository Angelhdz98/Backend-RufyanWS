package com.example.PaginaWebRufyan.domain.model;

import com.example.PaginaWebRufyan.domain.model.ValueObjects.BodyClotheTypesEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.PrintingTecniqueEnum;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ProductDomainDetails;
import lombok.Getter;

@Getter
public class BodyClothingDomainDetails extends ProductDomainDetails {
    private final String material;
    private final BodyClotheTypesEnum type; //posible enum
    private final PrintingTecniqueEnum printingTechnique;


    public BodyClothingDomainDetails( String material, BodyClotheTypesEnum type, PrintingTecniqueEnum printingTechnique) {
        this.material = material;
        this.type = type;
        this.printingTechnique = printingTechnique;
    }


    @Override
    public void validate() {
        if(material.isBlank())throw new IllegalArgumentException("Agrega valor de material");

    }
}
