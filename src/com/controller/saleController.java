package com.controller;

import com.controller.command.sale.saleCommandInterpreter;
import com.controller.command.sale.saleCommandParser;
import com.model.saleService;
import com.model.exceptions.SaleNotFoundException;

/**
 * Controlador del subsistema de ventas.
 * Es el especialista en creación de comandos en los referente a ventas.
 * 
 * @author JAVIER
 */

public class saleController {

	private saleService model; //Modelo del subsistema de pedidos

	/**
	 * Constructor de un parámetro del controlador del subsistema de ventas.
	 * @param order Modelo del subsistema de pedidos.
	 */
	
	public saleController(saleService sale) {
		model = sale;
		saleCommandInterpreter.configureCommandInterpreter(model);
		
	}

	/**
	 * Método encargado de generar un comando u otro en función del evento y del argumento que se pasan como parámetros.
	 * @param evento Evento del negocio que se quiere ejecutar.
	 * @param arg Parámetros del evento del negocio que se pasan como comando.
	 * @return Lo que devuelve el comando al ser ejecutado.
	 * @throws SaleNotFoundException 
	 */
	
	public Object actionSale(int evento, Object arg)  {

		saleCommandInterpreter command = saleCommandParser.parseCommand(evento, (String) arg);

		
		try {
			return command.execute();
		} catch (SaleNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
		
	}

}