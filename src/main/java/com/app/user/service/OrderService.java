package com.app.user.service;

import java.util.List;

import com.app.user.exceptions.UserNotFoundException;
import com.app.user.model.OrderEntity;

public interface OrderService {
	
	public List<OrderEntity> getAllOrdersByUserId(int userId) throws UserNotFoundException;
	
	public OrderEntity createOrder(int userId, OrderEntity orderEntity) throws UserNotFoundException;
	
	public OrderEntity getOrderByUserIdAndOrderId(int userId, int orderId) throws UserNotFoundException;
}
