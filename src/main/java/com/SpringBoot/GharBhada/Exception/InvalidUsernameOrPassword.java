package com.SpringBoot.GharBhada.Exception;

public class InvalidUsernameOrPassword extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUsernameOrPassword() {
		super("Invalid email or password.");
	}

}
