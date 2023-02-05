package com.aent.hotel.service.ratingService.exception;

public class InvalidNumberOfParametersException extends RuntimeException {

	public InvalidNumberOfParametersException() {
		super("Multiple filtering parameters not allowed");
	}

	public InvalidNumberOfParametersException(String message) {
		super(message);
	}
}
