package com.product.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="product_table")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_status")
	private String productStatus;//available or outOfStock
	
	@Column(name="stock_quantity")
	private Integer stockQuantity;
	
	@Column(name="product_price")
	private Double productPrice;
	
	@CreationTimestamp
	LocalDateTime createdAt;
	
	@UpdateTimestamp
	LocalDateTime updatedAt;


	
	
}
