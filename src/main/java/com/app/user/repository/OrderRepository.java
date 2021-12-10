package com.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.user.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
