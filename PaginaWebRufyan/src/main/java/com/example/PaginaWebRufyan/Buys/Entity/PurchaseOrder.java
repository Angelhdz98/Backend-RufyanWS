package com.example.PaginaWebRufyan.Buys.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.ToString;

@Data

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class PurchaseOrder implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String paymentId;
	@ManyToOne()
	private Long  userId;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<OrderItem> orderItems;
	private final LocalDateTime createdAt;
	@ManyToOne
	private OrderStatus orderStatus;
	private BigDecimal totalAmount;




    @Override
    public PurchaseOrder clone() {
        try {
            PurchaseOrder clone = (PurchaseOrder) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
