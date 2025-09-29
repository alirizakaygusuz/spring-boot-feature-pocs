package com.alirizakaygusuz.aop_poc.order.exception;

public class OrderAlreadyExistsException extends RuntimeException{

	public OrderAlreadyExistsException(String code) {
		super(code);
	}
}
