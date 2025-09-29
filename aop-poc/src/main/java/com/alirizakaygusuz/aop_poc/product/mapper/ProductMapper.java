package com.alirizakaygusuz.aop_poc.product.mapper;

import com.alirizakaygusuz.aop_poc.common.mapper.BaseMapper;
import com.alirizakaygusuz.aop_poc.product.dto.ProductCreateRequest;
import com.alirizakaygusuz.aop_poc.product.dto.ProductResponse;
import com.alirizakaygusuz.aop_poc.product.dto.ProductUpdateRequest;
import com.alirizakaygusuz.aop_poc.product.model.Product;

public interface ProductMapper extends BaseMapper<Product , ProductResponse , ProductCreateRequest ,ProductUpdateRequest> {
	
}
