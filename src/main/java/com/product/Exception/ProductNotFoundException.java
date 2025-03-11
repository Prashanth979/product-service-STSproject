package com.product.Exception;

public class ProductNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

		public ProductNotFoundException(String message) {
			super(message);
		}
}
