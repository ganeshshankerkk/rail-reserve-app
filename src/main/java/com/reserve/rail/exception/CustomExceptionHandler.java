package com.reserve.rail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.reserve.rail.response.ResponseMessage;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(TrainNotFoundException.class)
	public ResponseEntity<ResponseMessage> handleTrainNotFoundException(TrainNotFoundException trainNotFoundException){
		ResponseMessage message = new ResponseMessage();
		message.setErrorMessage(trainNotFoundException.getMessage());
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseMessage> handlePassengerNotFoundException(PassengerNotFoundException trainNotFoundException){
		ResponseMessage message = new ResponseMessage();
		message.setErrorMessage(trainNotFoundException.getMessage());
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
