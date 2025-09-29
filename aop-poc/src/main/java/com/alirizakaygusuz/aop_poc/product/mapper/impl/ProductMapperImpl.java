package com.alirizakaygusuz.aop_poc.product.mapper.impl;

import org.springframework.stereotype.Component;

import com.alirizakaygusuz.aop_poc.product.dto.ProductCreateRequest;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.dto.ProductUpdateRequest;
import com.alirizakaygusuz.aop_poc.product.mapper.ProductMapper;
import com.alirizakaygusuz.aop_poc.product.model.Product;


@Component
public class ProductMapperImpl implements ProductMapper {

	@Override
	public Product toEntity(ProductCreateRequest request) {
		return Product.builder()
				.code(request.getCode())
				.name(request.getName())
				.build();
	}

	@Override
	public ProductResponse toResponse(Product product) {
		return ProductResponse.builder()
				.code(product.getCode())
				.name(product.getName())
				.build();
	}

	@Override
	public void updateEntity(Product product, ProductUpdateRequest request) {
		product.setName(request.getName());
		
	}

}
