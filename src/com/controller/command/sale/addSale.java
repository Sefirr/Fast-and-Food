package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando addSale.
 * @author Javier
 *
 */

public class addSale extends saleCommandInterpreter {
	
	private int saleIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public addSale() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo saleIdentiffier de addSale.
	 * @param saleIdentiffier Es el parámetro del comando addSale "n", siendo "n" el número de venta.
	 */
	
	public addSale(int saleIdentiffier) {
		super();
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando addSale.
	 * @return Devuelve el resultado de la ejecución del comando addSale.
	 */

	@Override
	public Object execute() {
		service.addSale(saleIdentiffier);
		
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
			return new addSale(Integer.parseInt(arg0));
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
	 * @return Devuelve el evento de negocio correspondiente al comando addSale.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.AÑADIR_VENTA;
	}

}