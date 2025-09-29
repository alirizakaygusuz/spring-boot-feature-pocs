package com.alirizakaygusuz.aop_poc.order.dto;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;
import com.alirizakaygusuz.aop_poc.common.validation.annotation.PositiveNumberCustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
	
	@PositiveNumberCustom(message = "Price must be greater than 0")
	private double price;
	
	@NotEmptyCustom(message = "Order status cannot empty")
    private String status;
}
