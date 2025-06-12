package com.example.PaginaWebRufyan.Entity;
import com.example.PaginaWebRufyan.Components.OriginalStock;
import com.example.PaginaWebRufyan.Components.PaintingPriceManager;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@DiscriminatorValue("PAINTING")
@Entity
public class Painting extends Product {
	
	private Integer alturaCm;
	private Integer largoCm;
	private String medium;
	private String supportMaterial;

	@Embedded
	private PaintingPriceManager price;
	@Embedded
	private OriginalStock stock;

	final private  Integer minHeightCm = 15;
	final private  Integer minLargeCm= 15;
	final private  Integer minPrice = 750;
	final private Integer minPricePerCopy= 300;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private UserEntity originalOwner;


	

}