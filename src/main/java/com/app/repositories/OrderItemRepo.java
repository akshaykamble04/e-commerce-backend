package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
