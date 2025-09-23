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


    public BodyClothingDomainDetails( ) {
        this.material = "100% algodon ";
        this.type = BodyClotheTypesEnum.T_SHIRT;
        this.printingTechnique = PrintingTecniqueEnum.SERIGRAPHY;
    }


    public BodyClothingDomainDetails( String material, BodyClotheTypesEnum type, PrintingTecniqueEnum printingTechnique) {
        if(material.isBlank() || type.equals(null) || printingTechnique.equals(null)  ) throw new IllegalArgumentException("Faltan valores requeridos");
        this.material = material;
        this.type = type;
        this.printingTechnique = printingTechnique;
    }

    @Override
    public void validate() {
        if(material.isBlank())throw new IllegalArgumentException("Agrega valor de material");

    }
}
