package com.rajeshphysics.Exceptions;

public class AuthenticationException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6639421011166828681L;
	public AuthenticationException(String resourceName) {
		super(resourceName);
	}
}
