package com.app.user.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.user.exceptions.UserNotFoundException;
import com.app.user.model.OrderEntity;
import com.app.user.service.OrderService;

@RestController
@RequestMapping("/users")
public class OrderController {
	
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@GetMapping("/{userId}/orders")
	public List<OrderEntity> getAllOrdersByUserId(@PathVariable("userId") int userId) {
		try {
			return orderService.getAllOrdersByUserId(userId);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PostMapping("/{userId}/orders")
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity orderEntity, @PathVariable("userId") int userId) {
		try {
			OrderEntity savedOrder = orderService.createOrder(userId, orderEntity);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderId}").buildAndExpand(savedOrder.getOrderId()).toUri();
			return ResponseEntity.created(location).body(savedOrder);
		} 
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public OrderEntity getOrderByUserIdAndOrderId(@PathVariable("userId") int userId, @PathVariable("orderId") int orderId) {
		try {
			return orderService.getOrderByUserIdAndOrderId(userId, orderId);
		} 
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
