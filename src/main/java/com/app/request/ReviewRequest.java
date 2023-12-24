package com.app.request;

import lombok.Data;

@Data
public class ReviewRequest {
	
	private long productId;
	private String review;

}
