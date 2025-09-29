package com.alirizakaygusuz.aop_poc.product.service;

import java.util.List;

import com.alirizakaygusuz.aop_poc.product.dto.ProductCreateRequest;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.dto.ProductUpdateRequest;

public interface ProductService {
	
	ProductResponse createProduct(ProductCreateRequest request);
	
	ProductResponse getProductByCode(String code);
	
	List<ProductResponse> getAllProducts();
	
	ProductResponse updateProduct(String code , ProductUpdateRequest request);
	
	void deleteProduct(String code);

}
