package com.example.PaginaWebRufyan.Products.DTO.Painting;

import com.example.PaginaWebRufyan.Products.DTO.Product.ProductDTO;
import com.example.PaginaWebRufyan.Products.Entity.Painting;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper =true)
@ToString
@SuperBuilder
@Getter
public class PaintingDTO extends ProductDTO {


 private Integer alturaCm;
 private Integer largoCm;
 private String medium;
 private String supportMaterial;




}
