package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Painting;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper =true)
@ToString
@SuperBuilder
@Getter
public class PaintingDTO extends ProductDTO{


 private Integer alturaCm;
 private Integer largoCm;
 private String medium;
 private String supportMaterial;

 public PaintingDTO(Painting painting){
  super(painting.getName(),painting.getImage(),painting.getStyle(),painting.getCreationDate(),painting.getDescription(),painting.getPriceManager().getPriceMap(),painting.getStockManager().getStockMap(),painting.getAdditionalFeatures());
  this.alturaCm = painting.getAlturaCm();
  this.largoCm = painting.getLargoCm();
  this.medium = painting.getMedium();
  this.supportMaterial = painting.getSupportMaterial();

 }



}
