package com.qa.opencart.exception;

/**
 * This class is create to throw out own custom exceptions which is actually extending the topmost Exception class
 */
public class FrameworkException extends RuntimeException {

	public FrameworkException(String message)
	{
		
		super(message);
	}
	
}
