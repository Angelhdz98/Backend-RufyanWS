package com.example.PaginaWebRufyan.Buys.Controller;

import java.util.List;

import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Buys.Service.OrderService;

import jakarta.validation.constraints.Positive;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/orders")
	ResponseEntity<List<PurchaseOrder>> getAllOrders(){
		return  ResponseEntity.ok(orderService.findAllOrders());
	}
	
	@GetMapping("/orders/paged")
	ResponseEntity<Page<PurchaseOrder>> findAllOrdersPaged( Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersPaged(pageable));	
	}
	
	@GetMapping(value = "/orders/by-status")
	ResponseEntity<List<PurchaseOrder>> findAllOrdersByStatus(@RequestParam OrderStatus orderStatus){
		
		return ResponseEntity.ok(orderService.findAllOrdersByStatus(orderStatus));	
	}
	
	@GetMapping( value = "/orders/by-status/paged", params = "page")
	ResponseEntity<Page<PurchaseOrder>> findAllOrdersByStatusPaged(
								OrderStatus orderStatus,
										Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersByStatusPaged(orderStatus, pageable));	
	}
	@GetMapping("/orders/{userId}")
	ResponseEntity<List<PurchaseOrder>> findAllOrdersByUserId(
					@PathVariable @Positive(message = "El id debe de ser un numero positivo")
										int userId,
										Pageable pageable){
		
		return ResponseEntity.ok(orderService.findAllOrdersByUserId(userId));	
	}
	
	@GetMapping("/orders/{userId}/paged")
	ResponseEntity<Page<PurchaseOrder>> findAllOrdersByStatusPaged(
							@PathVariable @Positive(message = "El id debe de ser un numero positivo")
										Integer userId,
										Pageable pageable){
		return ResponseEntity.ok(orderService.findAllOrdersByUserIdPaged(userId, pageable));	
	}
	
	
}
