package com.model.exceptions;

@SuppressWarnings("serial")
public class ExchangeException extends Exception {

	public ExchangeException(String message) {
        super(message);
    }
    
    @Override
	public String getMessage() {
        return super.getMessage();
    }
    
}