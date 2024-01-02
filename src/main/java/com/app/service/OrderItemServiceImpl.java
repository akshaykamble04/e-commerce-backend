package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.OrderItem;
import com.app.repositories.OrderItemRepo;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemRepo orderItemRepo;
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		return orderItemRepo.save(orderItem);
	}

}
