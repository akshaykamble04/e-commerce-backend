package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.CartItem;
import com.app.entities.User;
import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.response.ApiResponse;
import com.app.service.CartItemService;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserService userService;

	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCartItem(user.getId(), cartItemId);

		ApiResponse res = new ApiResponse();
		res.setMessage("item removed from cart...!");
		res.setStatus(true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
			@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);

		return new ResponseEntity<CartItem>(updatedCartItem, HttpStatus.OK);
	}

}
