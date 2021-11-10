package com.team9.OrderMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.team9.OrderMS.Entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, String> {
	
	public List<OrderEntity> findByBuyerId(String buyerId);

	public Optional<OrderEntity> findByOrderId(String orderId);

}
