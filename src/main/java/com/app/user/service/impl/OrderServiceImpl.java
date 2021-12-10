package com.app.user.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.app.user.exceptions.OrderNotFoundException;
import com.app.user.exceptions.UserNotFoundException;
import com.app.user.model.OrderEntity;
import com.app.user.model.UserEntity;
import com.app.user.repository.OrderRepository;
import com.app.user.repository.UserRepository;
import com.app.user.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private UserRepository userRepository;
	
	private OrderRepository orderRepository;

	public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
		super();
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public List<OrderEntity> getAllOrdersByUserId(int userId) throws UserNotFoundException {
		return userRepository.findById(userId)
							 .map(user -> user.getOrders())
							 .orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}
	
	@Override
	public OrderEntity createOrder(int userId, OrderEntity orderEntity) throws UserNotFoundException {
		return userRepository.findById(userId)
					  		 .map(user -> this.saveOrder(user, orderEntity))
					  		 .orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}
	
	private OrderEntity saveOrder(UserEntity user, OrderEntity order) {
		order.setUserEntity(user);
		return orderRepository.save(order);
	}
	
	@Override
	public OrderEntity getOrderByUserIdAndOrderId(int userId, int orderId) throws UserNotFoundException {
		return userRepository.findById(userId)
					  .map(user -> getOrderById(orderId))
					  .orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}

	private OrderEntity getOrderById(int orderId)  {
		return orderRepository.findById(orderId)
							  .map(Function.identity())
							  .orElseThrow(() -> new OrderNotFoundException("Order Not Found"));
	}
}
