package com.udemy.demo.Exception;

public class UserNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2491157767077060200L;
	

	public UserNotFoundException(String messgae) {
		super(messgae);
	}
	
}
