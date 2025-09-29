package com.alirizakaygusuz.aop_poc.product.exception;

public class ProductNotFoundException  extends RuntimeException{

	public ProductNotFoundException(String productCode) {
		super("Product does not exist with product code: "+ productCode );
	}
}
