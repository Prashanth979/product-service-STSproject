package com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.service.ProductService;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//http://localhost:7070/product/add
	@PostMapping("/add")
	public ResponseEntity<ProductResponse>addProduct(@RequestBody ProductRequest productRequest){
		
		ProductResponse response = productService.addProduct(productRequest);
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
	
	//http://localhost:7070/product/all
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	
	//http://localhost:7070/product/id/productId
	@GetMapping("/id/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Integer productId){
		Product product = productService.getProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	
	//http://localhost:7070/product/update/productId
	@PutMapping("/update/{productId}")
	public ResponseEntity<String> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable Integer productId){
		
		String response = productService.updateProduct(productRequest,productId);
		
		if(response == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data not updated");
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(response);	
	}
	

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable Integer productId){
		String productResponse = productService.deleteProductById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);	
	}
}
