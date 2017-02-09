package com.model.exceptions;

@SuppressWarnings("serial")
public class DuplicateUserException extends Exception {
	
	public DuplicateUserException(String message) {
        super(message);
    }
    
    @Override
	public String getMessage() {
        return super.getMessage();
    }
    
}