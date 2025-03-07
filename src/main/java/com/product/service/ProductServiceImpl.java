package com.product.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	@Override
	public ProductResponse createProduct(ProductRequest productRequest) {
		
		Product response = productRepository.save(convertProductDtoToEntity(productRequest));
		
	return convertProductResponseToDto(response);
	}
	
	private ProductResponse convertProductResponseToDto(Product response) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductName(response.getProductName());
		productResponse.setProductId(response.getProductId());
		productResponse.setMessage("Product Successfully added into cart");
	return productResponse;
	}

	
	private Product convertProductDtoToEntity(ProductRequest productRequest) {
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setProductId(generateProductId());
		product.setProductStatus("ACTIVE");
		product.setProductPrice(productRequest.getProductPrice());
		product.setStockQuantity(productRequest.getStockQuantity());
	return product;
	}

	
	private Integer generateProductId() {
		Random random = new Random();
		int num = random.nextInt(5000,10000);
		return num;
	}
}
