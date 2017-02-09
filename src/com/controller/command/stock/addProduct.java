package com.controller.command.stock;

import com.controller.command.bussinessEvent;
import com.model.exceptions.ProductNotFoundException;

/**
 * Clase derivada de stockCommanInterpreter que representa el comando addProduct.
 * @author Javier
 *
 */

public class addProduct extends stockCommandInterpreter {
	
	private int id;
	private String name;
	private String Tag;
	private int amount;
	private double price;
	
	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * stockCommandInterpreter.
	 */
	
	public addProduct() {
		super();
	}
	
	/**
	 * Método constructor que inicializa los atributos de addProduct.
	 * @param id Identificador del producto que se va a añadir.
	 * @param name Nombre del producto que se va a añadir.
	 * @param Tag Etiqueta del producto que se va a añadir.
	 * @param amount Cantidad del producto que se va a añadir.
	 * @param price Precio del producto que se va a añadir.
	 */
	
	public addProduct(int id, String name, String Tag, int amount, double price) {
		super();
		this.id = id;
		this.name = name;
		this.Tag = Tag;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * Método de stockCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando addProduct.
	 * @return Devuelve el resultado de la ejecución del comando addProduct.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		try {
			service.addProduct(id, name, Tag, amount, price);
		} catch (ProductNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
	}

	@Override
	public stockCommandInterpreter Command(String arg0) {
		// TODO Auto-generated method stub
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
		try {
			return new addProduct(Integer.parseInt(arg0), arg1, arg2, Integer.parseInt(arg3), Double.valueOf(arg4));
		} catch(NumberFormatException e) {
			
		}
		
		return null;
		
	}

	@Override
	public stockCommandInterpreter Command(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Método de CommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando addProduct.
	 */

	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.AÑADIR_PRODUCTO_STOCK;
	}

}