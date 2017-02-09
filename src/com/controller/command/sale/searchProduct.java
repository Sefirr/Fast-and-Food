package com.controller.command.sale;

import com.controller.command.bussinessEvent;

/**
 * Clase derivada de saleCommandInterpreter que representa el comando searchProduct.
 * @author Javier
 *
 */

public class searchProduct extends saleCommandInterpreter {

	private int id;
	private String name;
	private int saleIdentiffier;

	/**
	 * Método constructor sin parámetros que llama al constructor de
	 * saleCommandInterpreter.
	 */
	
	public searchProduct() {
		super();
	}

	/**
	 * Método constructor que inicializa los atributos de searchProduct.
	 * @param id Identificador del producto que se va a buscar.
	 * @param name Nombre del producto que se va a buscar.
	 * @param saleIdentiffier Identificador de la venta en la que se va a buscar el producto.
	 */
	
	public searchProduct(int id, String name, int saleIdentiffier) {
		super();
		this.id = id;
		this.name = name;
		this.saleIdentiffier = saleIdentiffier;
	}
	
	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui correspondiente a la
	 * ejecución del comando searchProduct.
	 * @return Devuelve el resultado de la ejecución del comando searchProduct.
	 */

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return service.searchProduct(id, name, saleIdentiffier);
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
			return new searchProduct(Integer.parseInt(arg0), arg1,
					Integer.parseInt(arg2));
		} catch (NumberFormatException e) {

		}

		return null;

	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public saleCommandInterpreter Command(String arg0, String arg1,
			String arg2, String arg3, String arg4, String arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Método de saleCommandInterpreter que se sobreescribe aqui  y que devuelve el evento de negocio que se
	 * está ejecutando en todo momento.
	 * @return Devuelve el evento de negocio correspondiente al comando searchProduct.
	 */
	
	@Override
	public int bussinessEvent() {
		// TODO Auto-generated method stub
		return bussinessEvent.MOSTRAR_PRODUCTO;
	}

}