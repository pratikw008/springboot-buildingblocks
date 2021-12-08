package com.app.user.exceptions;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7544980989587515783L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
