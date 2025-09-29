package com.alirizakaygusuz.aop_poc.order.exception;

public class OrderNotFoundException extends RuntimeException{
	
	public OrderNotFoundException(String code){
		super(code);
	}

}
