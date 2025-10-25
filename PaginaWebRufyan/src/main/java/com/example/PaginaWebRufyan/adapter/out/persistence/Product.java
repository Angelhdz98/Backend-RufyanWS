
package com.example.PaginaWebRufyan.adapter.out.persistence;

import java.time.LocalDate;
import java.util.*;

import com.example.PaginaWebRufyan.Products.Enums.ProductTypeEnum;
import com.example.PaginaWebRufyan.adapter.out.PriceManagerPersist;
import com.example.PaginaWebRufyan.adapter.out.StockManager;
import com.example.PaginaWebRufyan.Image.Image;
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
	private PriceManagerPersist priceManagerPersist;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private StockManager stockManager;

	private Boolean isAvailable;
	//private PriceManager priceManager;
	private Boolean isFavorite;


	@OneToMany(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Image> image = new HashSet<>();
    private ProductTypeEnum productTypeEnum;


    protected Product(){
        this.name = "No Name";
        this.description = "No Description";
        this.creationDate = LocalDate.now();
        this.priceManagerPersist = null; // will be set on subclass
        this.stockManager = null; // will be set on subclass
        this.isAvailable = false;
        this.isFavorite = false;
        this.image = new HashSet<>();
        this.productTypeEnum = ProductTypeEnum.PAINTING;
    }
    //private StockManager stock;// a implementation of StockManager and PriceManager will be added on subclass

	}