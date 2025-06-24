package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.ProductsEnum;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateRegisterDTO {
    private String name ="";
    private ProductsEnum type = ProductsEnum.PAINTING;
    private String description = "";
    private LocalDate creationDate= LocalDate.now();
    private String style=" ";
    //private Integer price;
    private Boolean isFavorite=false;
    //private ProductsCategory productsCategory;
    private List<Image> oldImages = new ArrayList<>(List.of());
    private List<MultipartFile> newImageFiles = new ArrayList<>(List.of());

    private Map<String, Object> stock = new HashMap<>();
    private Map<String, Object> priceManage = new HashMap<>();
    private Map<String, String> additionalFeatures= new HashMap<>();

    public ProductUpdateRegisterDTO(ProductRegisterDTO productRegisterDTO, List<Image> images){
       this.name = productRegisterDTO.getName();
       this.type = productRegisterDTO.getType();
       this.description = productRegisterDTO.getDescription();
       this.creationDate = productRegisterDTO.getCreationDate();
       this.style = productRegisterDTO.getStyle();
       this.isFavorite = productRegisterDTO.getIsFavorite();
       this.oldImages = images;
       this.stock = productRegisterDTO.getStock();
       this.priceManage= productRegisterDTO.getPriceManage();
       this.additionalFeatures = productRegisterDTO.getAdditionalFeatures();

    }

}
