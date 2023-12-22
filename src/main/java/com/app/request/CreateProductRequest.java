package com.app.request;

import java.util.HashSet;
import java.util.Set;

import com.app.entities.Size;

import lombok.Data;

@Data
public class CreateProductRequest {
	
	private String title;
	
	private String description;
	
	private int price;
	
	private int discountedPrice;
	
	private int discountPercent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	private Set<Size> size = new HashSet<>();
	
	private String imageUrl;
	
	private String topLevelCategory;
	private String secondLevelCategory;
	private String thirdLevelCategory;
	

}
