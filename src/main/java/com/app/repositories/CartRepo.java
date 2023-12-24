package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {
	
	@Query("SELECT c From Cart c Where c.user.id=:userId")
	public Cart findByUserId(@Param("userId") Long userId);

}
