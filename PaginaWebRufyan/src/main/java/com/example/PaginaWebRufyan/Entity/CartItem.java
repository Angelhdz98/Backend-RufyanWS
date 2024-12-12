package com.example.PaginaWebRufyan.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CartItem {
	private int id; 
	private Cart cart;
	private Product product;
	private int quantity;
	
	
}
