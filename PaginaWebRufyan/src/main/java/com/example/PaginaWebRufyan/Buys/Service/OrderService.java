package com.example.PaginaWebRufyan.Buys.Service;


import java.util.List;

import com.example.PaginaWebRufyan.Buys.Entity.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Buys.Repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	// this and maybe all the findAll methods shall not be used,
	//could change for a pageable to control data transfer

	
	public List<PurchaseOrder>	findAllOrders(){
		
		return orderRepo.findAll(); 
	}
	
	public Page<PurchaseOrder>	findAllOrdersPaged(Pageable pageable){
		return orderRepo.findAll(pageable);
	}


	public List<PurchaseOrder> findAllOrdersByStatus(OrderStatus orderStatus){

		return orderRepo.findByOrderStatus(orderStatus);	
	}
	
	
	public Page<PurchaseOrder> findAllOrdersByStatusPaged(OrderStatus orderStatus, Pageable pageable){
		
		return orderRepo.findByOrderStatus(orderStatus, pageable);
	}
	
	public List<PurchaseOrder> findAllOrdersByUserId(Integer userId){
		return orderRepo.findByUserId(userId);
	}
	
	public Page<PurchaseOrder> findAllOrdersByUserIdPaged(Integer userId, Pageable pageable){
		return orderRepo.findByUserId(userId, pageable);
	}
	
	
	
	
	

}
