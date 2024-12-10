package com.example.PaginaWebRufyan.Entity;
import jakarta.persistence.Entity;
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
	
	private Integer altura_cm;
	private Integer largo_cm;
	private String medium;
	private String support_material;
	private Integer available_copies;
	private Integer copies_made;
	private Integer price_copy;
	
public	final Integer largoMin = 14;
public	final Integer alturaMin= 20;
	
public final Integer originalPriceMin= 500;
public	final Integer copyPriceMin= 200;
	
	
	

}