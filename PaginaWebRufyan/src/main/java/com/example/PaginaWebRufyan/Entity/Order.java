package com.example.PaginaWebRufyan.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
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
public class Order {
	private int id;
	private UserEntity user;
	private List<OrderItem> orderItems;
	private LocalDate createdAt;
	private OrderStatus orderStatus;
	private float totalAmount; 

}
