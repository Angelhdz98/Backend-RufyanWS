package com.example.PaginaWebRufyan.Buys.Entity;

import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor()
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
private String description;
private OrderStatusEnum orderStatusEnum;

public OrderStatus (String description, OrderStatusEnum orderStatusEnum){
    this.description = description;
    this.orderStatusEnum=orderStatusEnum;
}

}
