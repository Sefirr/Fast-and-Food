package com.controller.command.stock;

import com.controller.command.bussinessEvent;
import com.util.Tools.SortMethod;

/**
 * Clase derivada de stockCommanInterpreter que representa el comando sortStock.
 * @author Javier
 *
 */

public class sortStock extends stockCommandInterpreter {
	
	private SortMethod method;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * stockCommandInterpreter.
	 */
	
	public sortStock() {
		super();
	}

	/**
	 * Método que inicializa los atributos de sortStock.
	 * @param method Método que se va a emplear a la hora de clasificar inventario.
	 */
	
	public sortStock(SortMethod method) {
		this.method = method;
	}

	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando sortStock.
	 * @return Devuelve el resultado de la ejecución del comando sortStock.
	 */
	
	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.sortStock(method);
		
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
		try {
			SortMethod method = ((arg0.equalsIgnoreCase("NOMBRE")) ? SortMethod.NOMBRE : ((arg0.equalsIgnoreCase("CANTIDAD")) ? SortMethod.CANTIDAD : ((arg0.equalsIgnoreCase("TAG")) ? SortMethod.TAG : SortMethod.PRECIO)));
			return new sortStock(method);
		} catch(NumberFormatException e) {
			
		}
		
		return null;
		
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando sortStock.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.CLASIFICAR_INVENTARIO;
	}

}