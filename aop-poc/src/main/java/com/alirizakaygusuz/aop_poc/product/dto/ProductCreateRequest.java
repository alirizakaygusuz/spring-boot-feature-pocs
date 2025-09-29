package com.alirizakaygusuz.aop_poc.product.dto;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

	@NotEmptyCustom(message = "Product name cannot empty")
	private String name;
	
	@NotEmptyCustom(message = "Product code cannot empty")
	private String code;
}
