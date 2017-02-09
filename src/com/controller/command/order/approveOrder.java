package com.controller.command.order;

import com.controller.command.bussinessEvent;
import com.model.exceptions.OrderNotFoundException;

/**
 * Clase derivada de orderCommandInterpreter que representa el comando approveOrder.
 * @author Javier
 *
 */

public class approveOrder extends orderCommandInterpreter {
	
	private int orderIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public approveOrder() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo orderIdentiffier de approveOrder.
	 * @param orderIdentiffier Es el parámetro del comando approveOrder "n", siendo "n" el número de pedido.
	 */
	
	public approveOrder(int orderIdentiffier) {
		super();
		this.orderIdentiffier = orderIdentiffier;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando approveOrder.
	 * @return Devuelve el resultado de la ejecución del comando approveOrder.
	 */

	@Override
	public Object execute() {
		try {
			service.approve(orderIdentiffier);
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
	}
	
	@Override
	public orderCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		try {
			return new approveOrder(Integer.parseInt(arg0));
		} catch(NumberFormatException e) {
			
		}
		
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
	 * @return Devuelve el evento de negocio correspondiente al comando approveOrder.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.CONFIRMAR_PEDIDO;
	}

}