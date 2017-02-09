package com.model.exceptions;

/**
 * Producida cuando se intenta eliminar una cantidad de productos superior a la
 * existente.
 * 
 * @author Javier
 */
@SuppressWarnings("serial")
public class DuplicateProductException extends Exception {

	public DuplicateProductException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}