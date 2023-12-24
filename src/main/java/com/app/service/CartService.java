package com.app.service;

import com.app.entities.Cart;
import com.app.entities.User;
import com.app.exception.ProductException;
import com.app.request.AddItemRequest;

public interface CartService {
	
	Cart createCart(User user);
	
	String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	Cart findUserCart(Long userId);

}
