package com.alirizakaygusuz.aop_poc.order.dto;

import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	private String orderCode;
	private ProductResponse product;
	private double price;
	private String status;
	
}
