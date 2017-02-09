package com.model.exceptions;

@SuppressWarnings("serial")
public class InvalidLoginException extends Exception {

	public InvalidLoginException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}