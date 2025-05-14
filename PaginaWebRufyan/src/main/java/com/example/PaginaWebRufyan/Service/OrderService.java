package com.example.PaginaWebRufyan.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.Order;
import com.example.PaginaWebRufyan.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	// this and maybe all the findAll methods shall not be used,
	//could change for a pageable to control data transfer

	
	public List<Order>	findAllOrders(){
		
		return orderRepo.findAll(); 
	}
	
	public Page<Order>	findAllOrdersPaged(Pageable pageable){
		
		return orderRepo.findAll(pageable);
	}


	public List<Order> findAllOrdersByStatus(OrderStatus orderStatus){
		
		return orderRepo.findByOrderStatus(orderStatus);	
	}
	
	
	public Page<Order> findAllOrdersByStatusPaged(OrderStatus orderStatus, Pageable pageable){
		
		return orderRepo.findByOrderStatus(orderStatus, pageable);
	}
	
	public List<Order> findAllOrdersByUserId(Integer userId){
		return orderRepo.findByUserId(userId);
	}
	
	public Page<Order> findAllOrdersByUserIdPaged(Integer userId, Pageable pageable){
		return orderRepo.findByUserId(userId, pageable);
	}
	
	
	
	
	

}
