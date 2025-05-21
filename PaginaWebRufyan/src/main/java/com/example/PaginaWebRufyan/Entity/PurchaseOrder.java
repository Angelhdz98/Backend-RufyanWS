package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Builder
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private UserEntity user;
	@OneToMany
	private List<OrderItem> orderItems;
	private LocalDate createdAt;
	@ManyToOne
	private OrderStatus orderStatus;
	private float totalAmount; 

}
