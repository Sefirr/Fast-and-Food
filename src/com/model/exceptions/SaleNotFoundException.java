package com.model.exceptions;

@SuppressWarnings("serial")
public class SaleNotFoundException extends Exception {

	public SaleNotFoundException(String message) {
        super(message);
    }
    
    @Override
	public String getMessage() {
        return super.getMessage();
    }

}