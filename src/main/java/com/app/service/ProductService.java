package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entities.Product;
import com.app.exception.ProductException;
import com.app.request.CreateProductRequest;

public interface ProductService {

	Product createProduct(CreateProductRequest req);
	
	String deleteProduct(Long produtId) throws ProductException;
	
	Product updateProduct(Long productId,Product req) throws ProductException;
	
	Product findProductById(Long id) throws ProductException;
	
	List<Product> findProductByCategory(String category);
	
	Page<Product> getAllProduct(String category,List<String> colors,List<String> sizes,
			Integer minPrice,Integer maxPrice,Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);

	List<Product> findAllProducts();
	
}
