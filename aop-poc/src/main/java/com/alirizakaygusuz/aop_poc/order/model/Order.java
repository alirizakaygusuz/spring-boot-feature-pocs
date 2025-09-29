package com.alirizakaygusuz.aop_poc.order.model;

import com.alirizakaygusuz.aop_poc.common.audit.AuditBase;
import com.alirizakaygusuz.aop_poc.order.enums.OrderStatus;
import com.alirizakaygusuz.aop_poc.product.model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "orders")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AuditBase {
	
	
	
	
	@Column(unique = true , nullable = false)
	private String orderCode;
	
	@ManyToOne
	private Product product;

	
	private double price;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

}
