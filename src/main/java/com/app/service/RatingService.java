package com.app.service;

import java.util.List;

import com.app.entities.Rating;
import com.app.entities.User;
import com.app.exception.ProductException;
import com.app.request.RatingRequest;

public interface RatingService {

	Rating createRating(RatingRequest req,User user) throws ProductException;
	
	List<Rating> getProductsRating(Long productId);
}
