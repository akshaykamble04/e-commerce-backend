package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Address;
import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.User;
import com.app.exception.OrderException;
import com.app.repositories.AddressRepo;
import com.app.repositories.CartRepo;
import com.app.repositories.OrderItemRepo;
import com.app.repositories.OrderRepo;
import com.app.repositories.UserRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private OrderItemRepo orderItemRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private CartRepo cartRepo;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartService cartItemService;

	@Autowired
	private ProductService productService;

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address = addressRepo.save(shippingAddress);
		user.getAddress().add(address);
		userRepo.save(user);

		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem = orderItemRepo.save(orderItem);
			orderItems.add(createdOrderItem);
		}
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderitems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder = orderRepo.save(createdOrder);
		for(OrderItem item : orderItems) {
			item.setOrder(savedOrder);
			orderItemRepo.save(item);
		}
		
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		
		return orderRepo.findById(orderId).orElseThrow(()->new OrderException("order not exist with id "+orderId));
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		
		return orderRepo.getUsersOrders(userId);
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepo.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order= findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepo.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepo.save(order);
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepo.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepo.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		orderRepo.deleteById(orderId);
	}

}
