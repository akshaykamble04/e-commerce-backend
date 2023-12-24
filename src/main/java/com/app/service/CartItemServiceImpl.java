package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Product;
import com.app.entities.User;
import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.repositories.CartItemRepo;
import com.app.repositories.CartRepo;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepo cartItemRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		return cartItemRepo.save(cartItem);
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserId());
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		return cartItemRepo.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		return cartItemRepo.isCartItemExist(cart, product, size, userId);
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		User user = userService.findUserById(cartItem.getUserId());
		User reqUser = userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) 
			cartItemRepo.deleteById(cartItemId);
		else
			throw new UserException("You cant remove another users item");
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		
		return cartItemRepo.findById(cartItemId).orElseThrow(()-> new CartItemException("cart item not found with id: "+cartItemId));
	}

}
