package com.alirizakaygusuz.aop_poc.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alirizakaygusuz.aop_poc.product.dto.ProductCreateRequest;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.dto.ProductUpdateRequest;
import com.alirizakaygusuz.aop_poc.product.exception.ProductAlreadyExistsException;
import com.alirizakaygusuz.aop_poc.product.exception.ProductNotFoundException;
import com.alirizakaygusuz.aop_poc.product.mapper.ProductMapper;
import com.alirizakaygusuz.aop_poc.product.model.Product;
import com.alirizakaygusuz.aop_poc.product.repository.ProductRepository;
import com.alirizakaygusuz.aop_poc.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	@Override
	public ProductResponse createProduct(ProductCreateRequest request) {
		
		if(productRepository.existsByCode(request.getCode())) {
			throw new ProductAlreadyExistsException(request.getCode());
		}
		
		
		Product savedProduct = productRepository.save(productMapper.toEntity(request));

		return productMapper.toResponse(savedProduct);
	}

	@Override
	public ProductResponse getProductByCode(String code) {
		Product product = findProductByCodeOrThrowException(code);

		return productMapper.toResponse(product);

	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();

		List<ProductResponse> productResponses = new ArrayList<>();

		for (Product product : products) {
			productResponses.add(productMapper.toResponse(product));
		}
		return productResponses;
	}

	@Override
	public ProductResponse updateProduct(String code, ProductUpdateRequest request) {
		Product product = findProductByCodeOrThrowException(code);

		productMapper.updateEntity(product, request);

		Product updatedProduct = productRepository.save(product);

		return productMapper.toResponse(updatedProduct);
	}

	@Override
	public void deleteProduct(String code) {
		Product product = findProductByCodeOrThrowException(code);

		productRepository.delete(product);

	}

	private Product findProductByCodeOrThrowException(String code) {
		return productRepository.findByCode(code).orElseThrow(() -> new ProductNotFoundException(code));
	}

}
