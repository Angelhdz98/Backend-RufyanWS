package com.example.PaginaWebRufyan.Products.DTO.Painting;

import com.example.PaginaWebRufyan.Products.DTO.Product.ProductRegisterDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class PaintingRegisterDTO extends ProductRegisterDTO {
    private Integer alturaCm;
    private Integer largoCm;
    private String medium;
    private String supportMaterial;
    private Integer stockCopies;
    private Boolean isOriginalAvailable;
    private BigDecimal pricePerCopy;
    private BigDecimal pricePerOriginal;



}
