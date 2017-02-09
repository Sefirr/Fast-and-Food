package com.controller.command.order;

import com.controller.command.bussinessEvent;
import com.model.exceptions.OrderNotFoundException;

/**
 * Clase derivada de orderCommandInterpreter que representa el comando deleteOrder.
 * @author Javier
 *
 */

public class deleteOrder extends orderCommandInterpreter {
	
	private int orderIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public deleteOrder() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo orderIdentiffier de deleteOrder.
	 * @param orderIdentiffier Es el parámetro del comando deleteOrder "n", siendo "n" el número de pedido.
	 */
	
	public deleteOrder(int orderIdentiffier) {
		super();
		this.orderIdentiffier = orderIdentiffier;
	}

	@Override
	public Object execute() {
		try {
			service.deleteOrder(orderIdentiffier);
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		try {
			return new deleteOrder(Integer.parseInt(arg0));
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
	 * @return Devuelve el evento de negocio correspondiente al comando deleteOrder.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_PEDIDO;
	}


}