package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.config.JwtProvider;
import com.app.entities.User;
import com.app.exception.UserException;
import com.app.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws UserException {
		
		return userRepo.findById(userId).orElseThrow(()-> new UserException("User not found with id: "+userId));
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepo.findByEmail(email);
		if(user == null)
			throw new UserException("User not found with email: "+email);
		return user;
	}

}
