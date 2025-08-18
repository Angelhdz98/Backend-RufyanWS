package com.example.PaginaWebRufyan.Returns;

import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public abstract class ReturnOrder {
    @Id
    private Integer returnId =0;
    private BigDecimal returningAmount;
    private BigDecimal pricePerDelivery;
    private BigDecimal totalToReturn;
    private String address;
    public final Integer daysForReturning = 7;

    private ZonedDateTime returnRequestDate;
    private  OrderStatusEnum orderStatusEnum;

    public abstract Boolean isValidForReturning();
    public abstract BigDecimal calculateTotalToReturn();


    public ReturnOrder(Integer returnId, BigDecimal returningAmount, BigDecimal pricePerDelivery, BigDecimal totalToReturn, String address, ZonedDateTime returnRequestDate, OrderStatusEnum orderStatusEnum) {
        this.returnId = -1;
        this.returningAmount = returningAmount;
        this.pricePerDelivery = pricePerDelivery;
        this.totalToReturn = totalToReturn;
        this.address = address;
        this.returnRequestDate = ZonedDateTime.now();
        this.orderStatusEnum = orderStatusEnum;
    }
}
