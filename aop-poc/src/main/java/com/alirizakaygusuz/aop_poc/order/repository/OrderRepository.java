package com.alirizakaygusuz.aop_poc.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.aop_poc.order.model.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order , Long> {
	
	Optional<Order> findByOrderCode(String orderCode);
	
	boolean existsByOrderCode(String orderCode);

}
