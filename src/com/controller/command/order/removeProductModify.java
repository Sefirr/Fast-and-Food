/**
 * 
 */
package com.controller.command.order;

import com.controller.command.bussinessEvent;

/**
 * @author Javier
 *
 */
public class removeProductModify extends orderCommandInterpreter {
	
	private int id;
	private int amount;
	private int orderIdentiffier;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public removeProductModify() {
		super();
	}

	/**
	 *  Método constructor que inicializa los atributos de removeProduct.
	 * @param id Identificador del producto que se va a eliminar.
	 * @param amount Cantidad del producto que se va a eliminar.
	 * @param orderIdentiffier Identificador del pedido al que se le va a eliminar el producto en 
	 * la cantidad determinada por el parámetro amount.
	 */
	
	public removeProductModify(int id, int amount, int orderIdentiffier) {
		super();
		this.id = id;
		this.amount = amount;
		this.orderIdentiffier = orderIdentiffier;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando removeProduct.
	 * @return Devuelve el resultado de la ejecución del comando removeProduct.
	 */

	@Override
	public Object execute() {
		service.removeProductModify(id, amount, orderIdentiffier);
		
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		try {
			return new removeProductModify(Integer.parseInt(arg0),
					Integer.parseInt(arg1), Integer.parseInt(arg2));
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando removeProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_PRODUCTO_PEDIDO_MODIFY;
	}

}
