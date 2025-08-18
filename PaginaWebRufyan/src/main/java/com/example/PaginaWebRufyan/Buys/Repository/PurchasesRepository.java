package com.example.PaginaWebRufyan.Buys.Repository;

import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchasesRepository extends JpaRepository<PurchaseOrder,Integer> {

   Optional<PurchaseOrder> findPurchaseOrderByPaymentId(String paymentId);
}
