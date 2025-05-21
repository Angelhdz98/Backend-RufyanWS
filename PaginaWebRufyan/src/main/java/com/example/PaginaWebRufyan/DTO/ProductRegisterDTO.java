package com.example.PaginaWebRufyan.DTO;

import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ProductRegisterDTO {

    private String name;
    private String description;
    private LocalDate creationDate;
    private String style;
    private Integer price;
    private Boolean isFavorite;
    private ProductsCategory productsCategory;
    private List<MultipartFile> imageFiles;
    private Map<String, String> additionalFeatures = new LinkedHashMap<>();

}
