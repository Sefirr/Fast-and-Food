package com.controller.command.order;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de orderCommandInterpreter que representa el comando finishOrder.
 * @author Javier
 *
 */

public class finishOrder extends orderCommandInterpreter {

	private int orderIdentiffier;
	private String supplier;
	private String date;
	private int orderState;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public finishOrder() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de finishOrder.
	 * @param orderIdentiffier Identificador del pedido que se va a finalizar.
	 * @param supplier	Proveedor del pedido.
	 * @param date	Fecha en que se realiza el pedido.
	 */

	public finishOrder(int orderIdentiffier, String supplier, String date, int orderState) {
		super();
		this.orderIdentiffier = orderIdentiffier;
		this.supplier = supplier;
		this.date = date;
		this.orderState = orderState;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando finishOrder.
	 * @return Devuelve el resultado de la ejecución del comando finishOrder.
	 */

	@Override
	public Object execute() {
		service.finishOrder(orderIdentiffier, supplier, date, orderState);

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
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		try {
			return new finishOrder(Integer.parseInt(arg0), arg1, arg2, Integer.parseInt(arg3));
		} catch (NumberFormatException e) {

		}
		
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando finishOrder.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.FINALIZAR_PEDIDO;
	}

}