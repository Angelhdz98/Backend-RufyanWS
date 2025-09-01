package com.example.PaginaWebRufyan.Buys.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import com.example.PaginaWebRufyan.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data

@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Entity
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
	private final Temporal createdAt;
	@ManyToOne
	private OrderStatus orderStatus;
	private BigDecimal totalAmount;


	// se realizó la compra
    // este metodo contructor formara parte de un mapper en la capa de dominio
    /*
	public PurchaseOrder(ShoppingCart cart){
		this.userId = cart.getUserId();
		this.orderItems = cart.getItemList();
		this.createdAt = LocalDate.now();
		this.orderStatus = new OrderStatus(2,"Verificación de pago pendiente", OrderStatusEnum.PENDING);
		this.totalAmount = cart.getTotalAmount();

	}
     */


 /*   public PurchaseOrder(){
		this.createdAt = ZonedDateTime.now();

	}

  */





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
