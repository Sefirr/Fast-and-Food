package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando cancelSale.
 * @author Javier
 *
 */

public class cancelSale extends saleCommandInterpreter {
	
	private int saleIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public cancelSale() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo saleIdentiffier de cancelSale.
	 * @param orderIdentiffier Es el parámetro del comando cancelSale "n", siendo "n" el número de venta.
	 */
	
	public cancelSale(int saleIdentiffier) {
		super();
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando cancelSale.
	 * @return Devuelve el resultado de la ejecución del comando cancelSale.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.cancelSale(saleIdentiffier);
		
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
		try {
			return new cancelSale(Integer.parseInt(arg0));
		} catch(NumberFormatException e) {
			
		}
		
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
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2,
			String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2,
			String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2,
			String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando cancelSale.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.CANCELAR_VENTA;
	}

}
