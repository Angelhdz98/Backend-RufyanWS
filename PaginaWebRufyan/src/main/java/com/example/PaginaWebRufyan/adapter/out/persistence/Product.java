
package com.example.PaginaWebRufyan.adapter.out.persistence;

import java.time.LocalDate;
import java.util.*;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.PriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.StockManager;
import com.example.PaginaWebRufyan.Image.Image;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data

@AllArgsConstructor
@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Product {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private LocalDate creationDate;
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonProperty("productPricing")
	private PriceManagerPersist priceManagerPersist;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonProperty("productStock")
	private StockManager stockManager;

	private Boolean isAvailable;
	//private PriceManager priceManager;
	private Boolean isFavorite;


	@OneToMany(mappedBy = "product",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JsonManagedReference
	private Set<Image> images = new HashSet<>();
    private ProductTypeEnum productTypeEnum;


    protected Product(){
        this.name = "No Name";
        this.description = "No Description";
        this.creationDate = LocalDate.now();
        this.priceManagerPersist = null; // will be set on subclass
        this.stockManager = null; // will be set on subclass
        this.isAvailable = false;
        this.isFavorite = false;
        this.images = new HashSet<>();
        this.productTypeEnum = ProductTypeEnum.PAINTING;
    }
    //private StockManager stock;// a implementation of StockManager and PriceManager will be added on subclass

	}