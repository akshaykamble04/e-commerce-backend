package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

}
