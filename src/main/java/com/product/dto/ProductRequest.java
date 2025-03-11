package com.product.dto;


import lombok.Data;

@Data
public class ProductRequest {
	
	private String productName;
	
	private Integer stockQuantity;	
	
	private Double productPrice;

}
