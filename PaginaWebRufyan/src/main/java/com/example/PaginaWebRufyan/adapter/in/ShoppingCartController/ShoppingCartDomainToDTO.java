package com.example.PaginaWebRufyan.adapter.in.ShoppingCartController;

import com.example.PaginaWebRufyan.Exceptions.ResourceNotFoundException;
import com.example.PaginaWebRufyan.domain.model.CartItemDomain;
import com.example.PaginaWebRufyan.domain.model.ShoppingCartDomain;
import com.example.PaginaWebRufyan.domain.model.ValueObjects.ImageDomain;

import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCartDomainToDTO {

   public static ShoppingCartDTO toDTO(ShoppingCartDomain shoppingCartDomain){
        Set<CartItemDTORecord> cartItemDTORecordSet = shoppingCartDomain.getItems().stream().map((CartItemDomain item) -> {
            Set<ImageDomain> images = item.getProduct().getImages();
            ImageDomain firstImage = images.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("El producto no tiene imagenes"));
            return new CartItemDTORecord(item.getProduct().getName(), firstImage.url(), item.getDetails());
        }).collect(Collectors.toSet());
        return new ShoppingCartDTO(cartItemDTORecordSet,shoppingCartDomain.getSubtotalAmount());
    }
}
