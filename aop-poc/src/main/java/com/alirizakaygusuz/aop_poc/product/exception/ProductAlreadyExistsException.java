package com.alirizakaygusuz.aop_poc.product.exception;

public class ProductAlreadyExistsException extends RuntimeException {

	public ProductAlreadyExistsException(String productCode) {
		super("Product already exists with product code: "+ productCode);
	}
}
