package com.example.PaginaWebRufyan.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PaginaWebRufyan.Entity.Order;
import com.example.PaginaWebRufyan.Entity.OrderStatus;



public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByUserId(Integer userId);
	Page<Order> findByUserId(Integer userId, Pageable pageable);
	
	List<Order> findByOrderStatus(OrderStatus orderStatus);
	Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);
	
	
	
}
