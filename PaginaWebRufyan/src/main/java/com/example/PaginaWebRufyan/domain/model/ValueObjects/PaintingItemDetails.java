package com.example.PaginaWebRufyan.domain.model.ValueObjects;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


@Component
@Getter
public class PaintingItemDetails extends CartItemDetails {
    private final IsOriginalSelected isOriginalSelected;

    public PaintingItemDetails(Integer quantity,Boolean isOriginalSelected){
        super(quantity);
        if(isOriginalSelected && quantity > 1) throw new IllegalArgumentException("No se puede seleccionar obra original con cantidad mayor a 1");
        this.isOriginalSelected = new IsOriginalSelected(isOriginalSelected);
    }

    public PaintingItemDetails(){
        super(1);
        this.isOriginalSelected = new IsOriginalSelected(false);
    }


    @Override
    public String showDetails() {
        if(isOriginalSelected.getIsOriginalSelected()) return "Obra original seleccionada";
        else return "Numero de copias: "+ getItemQuantity();

    }

    @Override
    public Map<String, String> detailsObject() {
        Map<String, String> detailsObject = new LinkedHashMap<>();
        detailsObject.put(IsOriginalSelected.class.getSimpleName(), isOriginalSelected.toString());
        detailsObject.put(ItemQuantity.class.getSimpleName(),getItemQuantity().getQuantity().toString());
        return detailsObject;

    }

    @Override
    public boolean areSameDetails(CartItemDetails other) {
        if(!(other instanceof PaintingItemDetails paintingItemDetails) ) return false;
        return paintingItemDetails.getIsOriginalSelected().getIsOriginalSelected()==isOriginalSelected.getIsOriginalSelected() ;
    }

}
