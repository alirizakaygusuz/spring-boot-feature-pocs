package com.alirizakaygusuz.aop_poc.order.service;

import java.util.List;

import com.alirizakaygusuz.aop_poc.order.dto.OrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.OrderResponse;
import com.alirizakaygusuz.aop_poc.order.dto.OrderUpdateRequest;

public interface OrderService {
	
	OrderResponse createOrder(OrderCreateRequest request);
	
	OrderResponse getOrderByOrderCode(String code);
	
	List<OrderResponse> getAllOrders();
	
	OrderResponse updateOrder(String code , OrderUpdateRequest request);

	void deleteOrder(String code);
}
