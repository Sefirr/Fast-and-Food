/**
 * 
 */
package com.controller.command.order;

import com.controller.command.bussinessEvent;

/**
 * @author Javier
 *
 */
public class addProductModify extends orderCommandInterpreter {
	
	private int id;
	private String name;
	private String Tag;
	private int amount;
	private double price;
	private int orderIdentiffier;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * orderCommandInterpreter.
	 */
	
	public addProductModify() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de addProduct.
	 * @param id Identificador del producto que se va a añadir.
	 * @param name Nombre del producto que se va a añadir.
	 * @param Tag Etiqueta del producto que se va a añadir.
	 * @param amount Cantidad del producto que se va a añadir.
	 * @param price Precio del producto que se va a añadir.
	 * @param orderIdentiffier Identificador del pedido al que se le va a añadir el producto en 
	 * la cantidad determinada por el parámetro amount.
	 */
	
	public addProductModify(int id, String name, String Tag, int amount, double price, int orderIdentiffier) {
		super();
		this.id = id;
		this.name = name;
		this.Tag = Tag;
		this.amount = amount;
		this.price = price;
		this.orderIdentiffier = orderIdentiffier;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando addProduct.
	 * @return Devuelve el resultado de la ejecución del comando addProduct.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		service.addProductModify(id, name, Tag, amount, price, orderIdentiffier);
		
		return null;
	}

	@Override
	public orderCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
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
		try {
			return  new addProductModify(Integer.parseInt(arg0), arg1, arg2, Integer.parseInt(arg3), Double.valueOf(arg4), Integer.parseInt(arg5));
		} catch(NumberFormatException e) {
			
		}
		
		return null;
	}
	
	/**
	 * Método de orderCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando addProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.AÑADIR_PRODUCTO_PEDIDO_MODIFY;
	}


}
