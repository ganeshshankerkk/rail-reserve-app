package com.reserve.rail.exception;

public class PassengerNotFoundException extends RuntimeException{
	
	 private static final long serialVersionUID = -7574203302684510714L;

	public PassengerNotFoundException(String message) {
	        super(message);
	    }

}