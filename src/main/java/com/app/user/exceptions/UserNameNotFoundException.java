package com.app.user.exceptions;

public class UserNameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2181329285730009589L;

	public UserNameNotFoundException(String message) {
		super(message);
	}
}
