package com.example.PaginaWebRufyan.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@ToString
@Entity

public class Painting extends Product {
	
	private Integer alturaCm;
	private Integer largoCm;
	private String medium;
	private String supportMaterial;
	private Integer availableCopies;
	private Integer copiesMade;
	private Integer pricePerCopy;
	private Boolean isOriginalAvailable = true;
	final private  Integer minHeightCm = 15;
	final private  Integer minLargeCm= 15;
	final private  Integer minPrice = 750;
	final private Integer minPricePerCopy= 300;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private UserEntity originalOwner;


	

}