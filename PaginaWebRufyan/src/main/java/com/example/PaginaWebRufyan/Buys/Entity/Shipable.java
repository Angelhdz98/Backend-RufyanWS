package com.example.PaginaWebRufyan.Buys.Entity;

import java.math.BigDecimal;

// for clases you can deliver PurchaseOrder/Return
public interface Shipable {

    BigDecimal calculateShipmentCost();

}
