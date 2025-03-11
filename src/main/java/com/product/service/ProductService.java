package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;



@Service
public interface ProductService {

	ProductResponse addProduct(ProductRequest productRequest);

	List<Product> getAllProducts();

	Product getProductById(Integer productId);

	String updateProduct(ProductRequest productRequest, Integer productId);

	String deleteProductById(Integer productId);

	

		
}
