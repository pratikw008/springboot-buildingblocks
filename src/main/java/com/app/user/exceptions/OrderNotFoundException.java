package com.app.user.exceptions;

public class OrderNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523936270740635688L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
