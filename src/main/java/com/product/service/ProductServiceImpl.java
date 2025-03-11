package com.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.product.Exception.ProductNotFoundException;
import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.repository.ProductRepository;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Transactional
	@Override
	public ProductResponse addProduct(ProductRequest productRequest) {
		
		Product response = productRepository.save(convertProductDtoToEntity(productRequest));
		if(ObjectUtils.isEmpty(response)) {
			return null;
		}
		
	return convertProductResponseToDto(response);
	}
	
	
	private Product convertProductDtoToEntity(ProductRequest productRequest) {
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setProductCode(generateproductCode());
		product.setStockQuantity(productRequest.getStockQuantity());
		product.setProductPrice(productRequest.getProductPrice());
		product.setProductStatus("AVAILABLE");
		//status -- AVAILABLE OR OUT OF STOCK
		//IF stockquantity is greater than 1 it is available 
		//if less than 1 then it is out of stock
	return product;
	}
	
	
	private ProductResponse convertProductResponseToDto(Product response) {
		ProductResponse productResponse = new ProductResponse();
		//productResponse.setProductName(response.getProductName());
		productResponse.setProductId(response.getProductId());
		//productResponse.setStockQuantity(response.getStockQuantity());
		productResponse.setMessage(String.format("Product details saved with product code %s, product name %s, stock  %s, price %s", response.getProductCode(),
																																	response.getProductName(),
																																	response.getStockQuantity(),
																																	response.getProductPrice()));
		//productResponse.setMessage("Product Successfully added into cart");		
	return productResponse;
	}



	
	private String generateproductCode() {
		Random random = new Random();
		int prodCode = random.nextInt(5000,10000);
		return String.valueOf(prodCode);
	}


	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if (ObjectUtils.isEmpty(products)) {
			return new ArrayList<>();
		}
		return products;
	}


	@Override
	public Product getProductById(Integer productId) {
		Optional<Product>  product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new ProductNotFoundException(String.format("No Such Product Found for productId %s", productId));
		}
		return product.get();
	}


	@Override
	public String updateProduct(ProductRequest productRequest, Integer productId) {
		Product response = getProductById(productId);
		
		if (StringUtils.isNotBlank(productRequest.getProductName())) {
			response.setProductName(productRequest.getProductName());
		}
		if (response.getProductPrice()>0) {
			response.setProductPrice(productRequest.getProductPrice());
		}
		if (productRequest.getStockQuantity()>0) {
			response.setStockQuantity(productRequest.getStockQuantity());
		}
		
		Product updatedresponse = productRepository.save(response);
		if(updatedresponse == null) {
			return null;
		}
		return "Product Updated Successfully";
	}
/*
	Student response = getStudentDetailsByStudentId(studentId);
	if(StringUtils.isNotBlank(studentRequest.getName())) {
		response.setName(studentRequest.getName());
	}
	
	if(studentRequest.getMobileNumber()!=null && studentRequest.getMobileNumber()>0) {
		response.setMobileNumber(studentRequest.getMobileNumber());
	}
	
	if(StringUtils.isNotBlank(studentRequest.getEmailId())) {
		response.setEmailId(studentRequest.getEmailId());
	}
	
	
	Student updatedResponse = studentRepository.save(response);
	if(updatedResponse == null) {
		return null;
	}
	return "Updated Successfully";*/


	@Override
	public String deleteProductById(Integer productId) {
		productRepository.deleteById(productId);
		return "Product deleted successfully";
	}

}
