package com.venkat.exceptions;

public class InvalidProductInfoException extends Exception {
	
	private static final long serialVersionUID = 12345678916L;
	
	public InvalidProductInfoException(){
		super();
	}
	
	public InvalidProductInfoException(String message){
		super(message);
	}
}
