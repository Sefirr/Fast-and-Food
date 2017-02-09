package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando removeProduct.
 * @author Javier
 *
 */

public class removeProduct extends saleCommandInterpreter {

	private int id;
	private int amount;
	private int saleIdentiffier;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public removeProduct() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de removeProduct.
	 * @param id Identificador del producto que se va a eliminar.
	 * @param amount Cantidad del producto que se va a eliminar.
	 * @param saleIdentiffier Identificador de la venta a la que se le va a eliminar el producto en 
	 * la cantidad determinada por el parámetro amount.
	 */

	public removeProduct(int id, int amount, int saleIdentiffier) {
		super();
		this.id = id;
		this.amount = amount;
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando removeProduct.
	 * @return Devuelve el resultado de la ejecución del comando removeProduct.
	 */

	@Override
	public Object execute() {
		service.removeProduct(id, amount, saleIdentiffier);

		return null;
	}

	@Override
	public saleCommandInterpreter Command() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		try {
			return new removeProduct(Integer.parseInt(arg0),
					Integer.parseInt(arg1), Integer.parseInt(arg2));
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando removeProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_PRODUCTO_VENTA;
	}

}