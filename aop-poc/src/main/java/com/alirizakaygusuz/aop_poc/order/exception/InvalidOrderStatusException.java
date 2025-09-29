package com.alirizakaygusuz.aop_poc.order.exception;

public class InvalidOrderStatusException extends RuntimeException{

	public InvalidOrderStatusException(String status) {
		super("Invalid order status : "+ status);
	}
}
