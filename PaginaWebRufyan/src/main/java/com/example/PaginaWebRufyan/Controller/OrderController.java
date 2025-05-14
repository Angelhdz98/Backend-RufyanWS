package com.example.PaginaWebRufyan.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaginaWebRufyan.Entity.Order;
import com.example.PaginaWebRufyan.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Service.OrderService;

import jakarta.validation.constraints.Positive;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	ResponseEntity<List<Order>> getAllOrders(){
		return  ResponseEntity.ok(orderService.findAllOrders());
	}
	
	@GetMapping("/orders")
	ResponseEntity<Page<Order>> findAllOrdersPaged( Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersPaged(pageable));	
	}
	
	@GetMapping("/orders")
	ResponseEntity<List<Order>> findAllOrdersByStatus(OrderStatus orderStatus){
		
		return ResponseEntity.ok(orderService.findAllOrdersByStatus(orderStatus));	
	}
	
	@GetMapping("/orders")
	ResponseEntity<Page<Order>> findAllOrdersByStatusPaged(
							@RequestParam("status") OrderStatus orderStatus,
										Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersByStatusPaged(orderStatus, pageable));	
	}
	@GetMapping("/orders/{userId}")
	ResponseEntity<List<Order>> findAllOrdersByUserId(
					@PathVariable @Positive(message = "El id debe de ser un numero positivo")
										int userId,
										Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersByUserId(userId));	
	}
	
	@GetMapping("/orders/{userId}")
	ResponseEntity<Page<Order>> findAllOrdersByStatusPaged(
							@PathVariable @Positive(message = "El id debe de ser un numero positivo")
										Integer userId,
										Pageable pageable){
		return ResponseEntity.ok(orderService.findAllOrdersByUserIdPaged(userId, pageable));	
	}
	
	
}
