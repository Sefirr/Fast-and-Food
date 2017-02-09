package com.model.exceptions;

@SuppressWarnings("serial")
public class ApproveOrderException extends Exception {
	
	public ApproveOrderException(String message) {
        super(message);
    }
    
    @Override
	public String getMessage() {
        return super.getMessage();
    }
    
}