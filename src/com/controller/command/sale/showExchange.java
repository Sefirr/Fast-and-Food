package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando showExchange.
 * @author Javier
 *
 */

public class showExchange extends saleCommandInterpreter {
	
	private double pagado;
	private int saleIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public showExchange() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de showExchange.
	 * @param pagado Cantidad pagada por el cliente.
	 * @param saleIdentiffier Identificador de la venta a la que se va a mostrar el cambio tras pagarla.
	 */
	
	public showExchange(double pagado, int saleIdentiffier) {
		this.pagado = pagado;
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando showExchange.
	 * @return Devuelve el resultado de la ejecución del comando showExchange.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.showExchange(pagado, saleIdentiffier);
		
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
		try {
			return new showExchange(Double.valueOf(arg0), Integer.parseInt(arg1));
		} catch(NumberFormatException e) {
			
		}
		
		return null;
		
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando showExchange.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MOSTRAR_CAMBIO;
	}

}