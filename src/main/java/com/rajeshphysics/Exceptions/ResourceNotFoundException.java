package com.rajeshphysics.Exceptions;




public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7963886516388939815L;
	public ResourceNotFoundException(String resourceName) {
		super(resourceName);
	}
	
}
