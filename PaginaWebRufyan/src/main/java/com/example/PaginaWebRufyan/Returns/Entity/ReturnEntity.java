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
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReturnEntity extends ReturnOrder {


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
        return this.order.getCreatedAt().isBefore(order.getCreatedAt().plusWeeks(1));
    }

    @Override
    public BigDecimal calculateTotalToReturn() {
        BigDecimal updatedValue =  this.productsToReturn.stream().map(OrderItem::getPriceAtPurchase).reduce(BigDecimal.ZERO,BigDecimal::add); // sum of all element totals
        setReturningAmount(updatedValue);
        setTotalToReturn(updatedValue.subtract(getPricePerDelivery()));
        return updatedValue.subtract(getPricePerDelivery());
    }


}
