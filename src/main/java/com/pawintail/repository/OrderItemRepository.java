package com.pawintail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawintail.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
