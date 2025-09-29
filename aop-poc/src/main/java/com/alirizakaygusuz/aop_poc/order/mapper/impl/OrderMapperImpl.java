package com.alirizakaygusuz.aop_poc.order.mapper.impl;

import org.springframework.stereotype.Component;

import com.alirizakaygusuz.aop_poc.order.dto.OrderResponse;
import com.alirizakaygusuz.aop_poc.order.dto.OrderUpdateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.internal.InternalOrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.enums.OrderStatus;
import com.alirizakaygusuz.aop_poc.order.exception.InvalidOrderStatusException;
import com.alirizakaygusuz.aop_poc.order.mapper.OrderMapper;
import com.alirizakaygusuz.aop_poc.order.model.Order;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements OrderMapper {

	private final ProductMapper productMapper;

	@Override
	public Order toEntity(InternalOrderCreateRequest internalRequest) {
		return Order.builder().orderCode(internalRequest.getOrderCode()).product(internalRequest.getProduct()).price(internalRequest.getPrice())
				.status(OrderStatus.CREATED).build();
	}

	@Override
	public OrderResponse toResponse(Order order) {
		ProductResponse productResponse = productMapper.toResponse(order.getProduct());

		return OrderResponse.builder().orderCode(order.getOrderCode()).product(productResponse).price(order.getPrice())
				.status(order.getStatus().toString()).build();
	}

	@Override
	public void updateEntity(Order order, OrderUpdateRequest request) {

		if(request.getPrice() > 0) {
			order.setPrice(request.getPrice());
		}
		
		if (request.getStatus() != null) {
			switch (request.getStatus().toUpperCase()) {
			case "CREATED" -> order.setStatus(OrderStatus.CREATED);
			case "UPDATED" -> order.setStatus(OrderStatus.UPDATED);
			case "COMPLETED" -> order.setStatus(OrderStatus.COMPLETED);
			case "CANCELLED" -> order.setStatus(OrderStatus.CANCELLED);
			default ->throw new InvalidOrderStatusException(request.getStatus());
			}
		}

	}

	

	
	

}
