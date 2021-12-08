package com.app.user.exceptions;

public class UserExistsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1476057509570941552L;

	public UserExistsException(String message) {
		super(message);
	}
}
