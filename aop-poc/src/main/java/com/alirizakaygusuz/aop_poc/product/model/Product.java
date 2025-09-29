package com.alirizakaygusuz.aop_poc.product.model;

import com.alirizakaygusuz.aop_poc.common.audit.AuditBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AuditBase {

	private String name;
	
	@Column(unique = true , nullable = false)
	private String code;
}
