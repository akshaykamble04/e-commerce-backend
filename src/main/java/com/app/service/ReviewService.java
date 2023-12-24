package com.app.service;

import java.util.List;

import com.app.entities.Review;
import com.app.entities.User;
import com.app.exception.ProductException;
import com.app.request.ReviewRequest;

public interface ReviewService {
	
	Review createReview(ReviewRequest req,User user) throws ProductException;

	List<Review> getAllReview(Long productId);
}
