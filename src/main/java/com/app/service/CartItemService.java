package com.app.service;

import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Product;
import com.app.exception.CartItemException;
import com.app.exception.UserException;

public interface CartItemService {
	
	CartItem createCartItem(CartItem cartItem);
	
	CartItem updateCartItem(Long userId,Long id, CartItem cartItem) throws CartItemException,UserException;
	
	CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
	
	CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
