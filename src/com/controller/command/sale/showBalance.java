package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando showBalance.
 * @author Javier
 *
 */

public class showBalance extends saleCommandInterpreter {
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public showBalance() {
		super();
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando showBalance.
	 * @return Devuelve el resultado de la ejecución del comando showBalance.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.showBalance();
		
		return null;
		
	}

	@Override
	public saleCommandInterpreter Command() {
		// TODO Auto-generated method stub
		try {
			return new showBalance();
		} catch(NumberFormatException e) {
			
		}
		
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
	 * @return Devuelve el evento de negocio correspondiente al comando showBalance.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MOSTRAR_BALANCE;
	}

}