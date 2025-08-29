package com.example.PaginaWebRufyan.Buys.Repository;

import com.example.PaginaWebRufyan.Buys.Entity.OrderStatus;
import com.example.PaginaWebRufyan.Buys.Enums.OrderStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    Optional<OrderStatus> findByOrderStatusEnum(OrderStatusEnum orderStatusEnum);
}
