package com.example.PaginaWebRufyan.Buys.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;



public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	List<PurchaseOrder> findByUserId(Integer userId);
	Page<PurchaseOrder> findByUserId(Integer userId, Pageable pageable);
	
	List<PurchaseOrder> findByOrderStatus(OrderStatus orderStatus);
	Page<PurchaseOrder> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);
	
	
	
}
