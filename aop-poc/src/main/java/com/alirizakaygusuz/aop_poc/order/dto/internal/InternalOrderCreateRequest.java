package com.alirizakaygusuz.aop_poc.order.dto.internal;


import com.alirizakaygusuz.aop_poc.product.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InternalOrderCreateRequest {

	private Product product;
	
	private double price;
	
	private String orderCode;
}
