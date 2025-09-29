package com.alirizakaygusuz.aop_poc.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alirizakaygusuz.aop_poc.order.dto.OrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.OrderResponse;
import com.alirizakaygusuz.aop_poc.order.dto.OrderUpdateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.internal.InternalOrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.enums.OrderStatus;
import com.alirizakaygusuz.aop_poc.order.exception.OrderNotFoundException;
import com.alirizakaygusuz.aop_poc.order.mapper.OrderMapper;
import com.alirizakaygusuz.aop_poc.order.model.Order;
import com.alirizakaygusuz.aop_poc.order.repository.OrderRepository;
import com.alirizakaygusuz.aop_poc.order.service.OrderService;
import com.alirizakaygusuz.aop_poc.product.exception.ProductNotFoundException;
import com.alirizakaygusuz.aop_poc.product.model.Product;
import com.alirizakaygusuz.aop_poc.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	
	private final OrderMapper orderMapper;
	
	private final  ProductRepository productRepository; 
	

	@Override
	public OrderResponse createOrder(OrderCreateRequest request) {
		String orderCode = UUID.randomUUID().toString();
		
	
		Product product = findProductByProductCodeOrThrowException(request.getProductCode());
		
		InternalOrderCreateRequest internalRequest= InternalOrderCreateRequest.builder()
				.orderCode(orderCode)
				.product(product)
				.price(request.getPrice())
				.build();

		Order order = orderMapper.toEntity(internalRequest);

		Order savedOrder = orderRepository.save(order);

		return orderMapper.toResponse(savedOrder);
	}
	
	

	private Product findProductByProductCodeOrThrowException(String productCode) {
		return productRepository.findByCode(productCode).orElseThrow(() -> new ProductNotFoundException(productCode));
	}

	@Override
	public OrderResponse getOrderByOrderCode(String code) {
		Order order = findOrderByCodeOrThrowException(code);

		return orderMapper.toResponse(order);
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();

		List<OrderResponse> orderResponses = new ArrayList<>();

		for (Order order : orders) {
			orderResponses.add(orderMapper.toResponse(order));
		}

		return orderResponses;
	}

	@Override
	public OrderResponse updateOrder(String code, OrderUpdateRequest request) {
		Order order = findOrderByCodeOrThrowException(code);

		orderMapper.updateEntity(order, request);
		
		Order updatedOrder = orderRepository.save(order);

		return orderMapper.toResponse(updatedOrder);
	}

	@Override
	public void deleteOrder(String code) {
		Order order = findOrderByCodeOrThrowException(code);

		order.setStatus(OrderStatus.CANCELLED);
		
		orderRepository.save(order);

	}

	private Order findOrderByCodeOrThrowException(String code) {
		return orderRepository.findByOrderCode(code).orElseThrow(() -> new OrderNotFoundException(code));
	}

}
