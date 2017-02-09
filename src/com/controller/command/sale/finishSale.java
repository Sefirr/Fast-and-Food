package com.controller.command.sale;

import com.controller.command.bussinessEvent;
import com.model.exceptions.SaleNotFoundException;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando finishSale.
 * @author Javier
 *
 */

public class finishSale extends saleCommandInterpreter {
	
	private int saleIdentiffier;
	private String customer;
	private String date;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public finishSale() {
		super();
	}
	
	/**
	 * Método constructor que inicializa el atributo saleIdentiffier de finishSale. 
	 * @param saleIdentiffier Es el parámetro del comando finishSale "n", siendo "n" el número de venta.
	 * @param customer Es el cliente de la venta.
	 * @param date Fecha en la que ha sido realizada la venta.
	 */
	
	public finishSale(int saleIdentiffier, String customer, String date) {
		super();
		this.saleIdentiffier = saleIdentiffier;
		this.customer = customer;
		this.date = date;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando finishSale.
	 * @return Devuelve el resultado de la ejecución del comando finishSale.
	 * @throws SaleNotFoundException 
	 */

	@Override
	public Object execute() throws SaleNotFoundException {
		// TODO Auto-generated method stub
		service.finishSale(saleIdentiffier, customer, date);
		
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
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		try {
			return new finishSale(Integer.parseInt(arg0), arg1, arg2);
		} catch(NumberFormatException e) {
			
		}
		
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
	 * @return Devuelve el evento de negocio correspondiente al comando finishSale.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.FINALIZAR_VENTA;
	}

}
