package com.alirizakaygusuz.aop_poc.product.dto;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
	@NotEmptyCustom(message = "Product name cannot empty")
    private String name;

}
