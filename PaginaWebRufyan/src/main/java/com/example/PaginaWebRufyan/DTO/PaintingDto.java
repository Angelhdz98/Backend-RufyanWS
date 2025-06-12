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
public class PaintingDto extends ProductDTO{


 private Integer alturaCm;
 private Integer largoCm;
 private String medium;
 private String supportMaterial;

 public PaintingDto(Painting painting){
  super();
  this.alturaCm = painting.getAlturaCm();
  this.largoCm = painting.getLargoCm();
  this.medium = painting.getMedium();
  this.supportMaterial = painting.getSupportMaterial();

 }



}
