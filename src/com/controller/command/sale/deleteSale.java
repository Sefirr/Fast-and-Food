package com.controller.command.sale;

import com.controller.command.bussinessEvent;
import com.model.exceptions.SaleNotFoundException;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando deleteSale.
 * @author Javier
 *
 */

public class deleteSale extends saleCommandInterpreter {
	
	private int saleIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public deleteSale() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo saleIdentiffier de deleteSale.
	 * @param saleIdentiffier Es el parámetro del comando deteleSale "n", siendo "n" el número de venta.
	 */
	
	public deleteSale(int orderIdentiffier) {
		super();
		this.saleIdentiffier = orderIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando deleteSale.
	 * @return Devuelve el resultado de la ejecución del comando deleteSale.
	 */

	@Override
	public Object execute() {
		try {
			service.deleteSale(saleIdentiffier);
		} catch (SaleNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
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
			return new deleteSale(Integer.parseInt(arg0));
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
	 * @return Devuelve el evento de negocio correspondiente al comando deleteSale.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.ELIMINAR_VENTA;
	}


}