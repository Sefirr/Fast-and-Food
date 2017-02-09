package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando addProduct.
 * @author Javier
 *
 */

public class addProduct extends saleCommandInterpreter {
	
	private int id;
	private int amount;
	private int saleIdentiffier;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public addProduct() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de addProduct.
	 * @param id Identificador del producto que se va a añadir.
	 * @param amount Cantidad del producto que se va a añadir.
	 * @param saleIdentiffier Identificador de la venta a la que se le va a añadir el producto en 
	 * la cantidad determinada por el parámetro amount.
	 */
	
	public addProduct(int id, int amount, int saleIdentiffier) {
		super();
		this.id = id;
		this.amount = amount;
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando addProduct.
	 * @return Devuelve el resultado de la ejecución del comando addProduct.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.addProduct(id, amount, saleIdentiffier);
		
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
			return new addProduct(Integer.parseInt(arg0), Integer.parseInt(arg1), Integer.parseInt(arg2));
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
		return Command(arg0, arg1, arg2, arg3, arg4, arg5);
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando addProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.AÑADIR_PRODUCTO_VENTA;
	}

}