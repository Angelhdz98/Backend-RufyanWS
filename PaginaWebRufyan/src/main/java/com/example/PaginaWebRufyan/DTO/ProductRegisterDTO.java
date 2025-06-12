package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Entity.ProductsEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Getter
public class ProductRegisterDTO {

    private String name;
    private ProductsEnum type;
    private String description;
    private LocalDate creationDate;
    private String style;
    //private Integer price;
    private Boolean isFavorite;
    //private ProductsCategory productsCategory;
    private List<MultipartFile> imageFiles;
    private Map<String, Object> stock;
    private Map<String, Object> priceManage;
    private Map<String, String> additionalFeatures;


    //private Map<String, String> additionalFeatures = new LinkedHashMap<>();

}
