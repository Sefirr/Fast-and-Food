package com.controller.command.stock;

import com.controller.command.bussinessEvent;
import com.model.exceptions.ProductNotFoundException;

/**
 * Clase derivada de stockCommanInterpreter que representa el comando searchProduct.
 * @author Javier
 *
 */

public class searchProduct extends stockCommandInterpreter {

	private int id;
	private String name;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * stockCommandInterpreter.
	 */
	
	public searchProduct() {
		super();
	}

	/**
	 * Método constructor que inicializa los atributos de searchProduct.
	 * @param id Identificador del producto que se va a buscar.
	 * @param name Nombre del producto que se va a buscar.
	 */
	
	public searchProduct(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando searchProduct.
	 * @return Devuelve el resultado de la ejecución del comando searchProduct.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			return service.searchProduct(id, name);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block

		}

		return null;

	}

	@Override
	public stockCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		try {
			return new searchProduct(Integer.parseInt(arg0), arg1);
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando searchProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MOSTRAR_PRODUCTO;
	}

}