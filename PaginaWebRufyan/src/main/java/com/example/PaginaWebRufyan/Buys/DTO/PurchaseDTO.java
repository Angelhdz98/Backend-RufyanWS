package com.example.PaginaWebRufyan.Buys.DTO;

import com.example.PaginaWebRufyan.Buys.Entity.OrderItem;
import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PurchaseDTO {
    private String username ="";
    private String paymentId ="";
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDate createdAt;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;

    public PurchaseDTO(PurchaseOrder purchase) {
        this.username = purchase.getUser().getUsername();
        this.paymentId = purchase.getPaymentId();
        this.orderItems = purchase.getOrderItems();
        this.createdAt = purchase.getCreatedAt();
        this.orderStatus = purchase.getOrderStatus();
        this.totalAmount = purchase.getTotalAmount();
    }
}
