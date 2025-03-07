package com.product.service;

import org.springframework.stereotype.Service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;



@Service
public interface ProductService {

	ProductResponse createProduct(ProductRequest productRequest);

	
	
	
}
