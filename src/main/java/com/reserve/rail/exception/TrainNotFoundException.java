package com.reserve.rail.exception;

public class TrainNotFoundException extends RuntimeException{
	
	 private static final long serialVersionUID = -7574203302684510714L;

	public TrainNotFoundException(String message) {
	        super(message);
	    }

}