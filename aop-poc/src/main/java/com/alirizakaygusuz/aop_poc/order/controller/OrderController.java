package com.alirizakaygusuz.aop_poc.order.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;
import com.alirizakaygusuz.aop_poc.order.dto.OrderCreateRequest;
import com.alirizakaygusuz.aop_poc.order.dto.OrderResponse;
import com.alirizakaygusuz.aop_poc.order.dto.OrderUpdateRequest;
import com.alirizakaygusuz.aop_poc.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponse> createOrder(
			@RequestBody
			OrderCreateRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
	}

	@GetMapping("/{code}")
	public ResponseEntity<OrderResponse> getOrderByOrderCode(
			@NotEmptyCustom
			@PathVariable String code) {
		return ResponseEntity.ok(orderService.getOrderByOrderCode(code));
	}

	@GetMapping
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@PatchMapping("/{code}")
	public ResponseEntity<OrderResponse> updateOrder(
			@NotEmptyCustom
			@PathVariable 
			String code , 
			@RequestBody 
			OrderUpdateRequest request) {
		return ResponseEntity.ok(orderService.updateOrder(code , request));
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<Void> deleteOrder(
			@NotEmptyCustom
			@PathVariable 
			String code) {
		orderService.deleteOrder(code);
		return ResponseEntity.noContent().build();
		
	}

}
