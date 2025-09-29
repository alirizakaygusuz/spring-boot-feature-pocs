package com.alirizakaygusuz.aop_poc.product.controller;

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
import com.alirizakaygusuz.aop_poc.product.dto.ProductCreateRequest;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.dto.ProductUpdateRequest;
import com.alirizakaygusuz.aop_poc.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<ProductResponse> createProduct(
			@RequestBody 
			ProductCreateRequest request){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(request));
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<ProductResponse> getProductByCode(
			@NotEmptyCustom
			@PathVariable String code){
		return ResponseEntity.ok(productService.getProductByCode(code));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	
	@PatchMapping("/{code}")
	public ResponseEntity<ProductResponse> updateProduct(
			@NotEmptyCustom
			@PathVariable String code ,
			@RequestBody 
			ProductUpdateRequest request){
		return ResponseEntity.ok(productService.updateProduct(code , request));
	}
	
	
	@DeleteMapping("/{code}")
	public ResponseEntity<Void> deleteProduct(
			@NotEmptyCustom
			@PathVariable 
			String code){
		productService.deleteProduct(code);
		
		return ResponseEntity.noContent().build();
	}
	
 
}
