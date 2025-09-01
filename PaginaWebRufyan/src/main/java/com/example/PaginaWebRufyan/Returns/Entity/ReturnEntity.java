package com.example.PaginaWebRufyan.Returns.Entity;


import com.example.PaginaWebRufyan.Buys.Entity.OrderItem;
import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Returns.ReturnOrder;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

//@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReturnEntity extends ReturnOrder {

    public static Long WEEKS_FOR_RETURNING= 2L;

    @OneToOne(cascade = CascadeType.MERGE)

    private PurchaseOrder order;
    @OneToMany(cascade = CascadeType.MERGE )
    private Set<OrderItem> productsToReturn;

    public ReturnEntity(PurchaseOrder order, Set<OrderItem> productsToReturn) {
        this.order = order;
        this.productsToReturn = productsToReturn;
    }

    public ReturnEntity(Integer returnId, BigDecimal returningAmount, BigDecimal pricePerDelivery, BigDecimal totalToReturn, String address, PurchaseOrder order, Set<OrderItem> productsToReturn) {
 ;
        this.order = order;
        this.productsToReturn = productsToReturn;
    }

    @Override
    public Boolean isValidForReturning() {
        // the only thing to care about returns is time before a week
        return ChronoUnit.WEEKS.between(this.order.getCreatedAt(), LocalDate.now())<WEEKS_FOR_RETURNING;//.isBefore(order.getCreatedAt().plusWeeks(1))
    }

    @Override
    public BigDecimal calculateTotalToReturn() {
        BigDecimal updatedValue =  this.productsToReturn.stream().map(OrderItem::getPriceAtPurchase).reduce(BigDecimal.ZERO,BigDecimal::add); // sum of all element totals
        setReturningAmount(updatedValue);
        setTotalToReturn(updatedValue.subtract(getPricePerDelivery()));
        return updatedValue.subtract(getPricePerDelivery());
    }


}
