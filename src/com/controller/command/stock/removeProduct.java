package com.controller.command.stock;

import com.controller.command.bussinessEvent;
import com.model.exceptions.ProductAmountException;
import com.model.exceptions.ProductNotFoundException;

/**
 * Clase derivada de stockCommanInterpreter que representa el comando removeProduct.
 * @author Javier
 *
 */

public class removeProduct extends stockCommandInterpreter {

	private int id;
	private int amount;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * stockCommandInterpreter.
	 */
	
	public removeProduct() {
		super();
	}

	/**
	 * Método constructor que inicializa los atributos de removeProduct.
	 * @param id Identificador del producto que se va a eliminar.
	 * @param amount Cantidad del producto que se va a eliminar.
	 */
	
	public removeProduct(int id, int amount) {
		super();
		this.id = id;
		this.amount = amount;
	}

	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando removeProduct.
	 * @return Devuelve el resultado de la ejecución del comando removeProduct.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			service.removeProduct(id, amount);
		} catch (ProductNotFoundException | ProductAmountException e) {
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
			return new removeProduct(Integer.parseInt(arg0),
					Integer.parseInt(arg1));
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
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando removeProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_PRODUCTO_STOCK;
	}

}
