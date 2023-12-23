package com.app.service;

import java.util.List;

import com.app.entities.Address;
import com.app.entities.Order;
import com.app.entities.User;
import com.app.exception.OrderException;

public interface OrderService {
	
	Order createOrder(User user,Address shippingAddress);
	
	Order findOrderById(Long orderId) throws OrderException;
	
	List<Order> usersOrderHistory(Long userId);
	
	Order placedOrder(Long orderId) throws OrderException;
	
	Order confirmedOrder(Long orderId) throws OrderException;
	
	Order shippedOrder(Long orderId) throws OrderException;
	
	Order deliveredOrder(Long orderId) throws OrderException;
	
	Order canceledOrder(Long orderId) throws OrderException;
	
	List<Order> getAllOrders();
	
	void deleteOrder(Long orderId) throws OrderException;
	

}
