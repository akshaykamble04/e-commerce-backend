package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entities.User;
import com.app.repositories.UserRepo;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	
	
//	public CustomUserServiceImplementation(UserRepo userRepo) {
//		
//		this.userRepo = userRepo;
//	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found with email: "+username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

	

}
