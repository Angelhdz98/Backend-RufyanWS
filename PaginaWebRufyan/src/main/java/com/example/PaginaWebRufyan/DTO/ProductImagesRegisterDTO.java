package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.Image;
import com.example.PaginaWebRufyan.Entity.ProductsEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductImagesRegisterDTO {
    private String name;
    private ProductsEnum type;
    private String description;
    private LocalDate creationDate;
    private String style;
    //private Integer price;
    private Boolean isFavorite;
    //private ProductsCategory productsCategory;
    private List<Image> images;
    private Map<String, Object> stock;
    private Map<String, Object> priceManage;
    private Map<String, String> additionalFeatures;

    public ProductImagesRegisterDTO(ProductRegisterDTO productRegisterDTO, List<Image> images){
       this.name = productRegisterDTO.getName();
       this.type = productRegisterDTO.getType();
       this.description = productRegisterDTO.getDescription();
       this.creationDate = productRegisterDTO.getCreationDate();
       this.style = productRegisterDTO.getStyle();
       this.isFavorite = productRegisterDTO.getIsFavorite();
       this.images= images;
       this.stock = productRegisterDTO.getStock();
       this.priceManage= productRegisterDTO.getPriceManage();
       this.additionalFeatures = productRegisterDTO.getAdditionalFeatures();

    }

}
