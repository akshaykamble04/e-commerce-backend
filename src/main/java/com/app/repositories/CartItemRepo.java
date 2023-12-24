package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Cart;
import com.app.entities.CartItem;
import com.app.entities.Product;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

	
	@Query("SELECT ci From CartItem ci Where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart,@Param("product") Product product,
			         				@Param("size") String size,@Param("userId") Long userId);
}
