package com.alirizakaygusuz.aop_poc.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alirizakaygusuz.aop_poc.product.model.Product;


public interface ProductRepository extends JpaRepository<Product , Long>{

	boolean existsByCode(String code);

	Optional<Product> findByCode(String productCode);

}
