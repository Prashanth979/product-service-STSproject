package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductRequest productRequest){
		
		ProductResponse response = productService.createProduct(productRequest);
		if(response == null){
			ProductResponse prodResponse = new ProductResponse();
			prodResponse.setProductId(null);
			prodResponse.setMessage("Data not saved");

		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(response);

		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
}
