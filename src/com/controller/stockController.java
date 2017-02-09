package com.controller;

import com.controller.command.stock.stockCommandInterpreter;
import com.controller.command.stock.stockCommandParser;
import com.model.stockService;

/**
 * Controlador del subsistema de almacén.
 * Es el especialista en creación de comandos en los referente a almacén.
 * 
 * @author JAVIER
 */

public class stockController {

	private stockService model; // Modelo del subsistema de almacén.

	/**
	 * Constructor de un parámetro del controlador del subsistema de almacén.
	 * @param order Modelo del subsistema de almacén.
	 */
	
	public stockController(stockService stock) {
		model = stock;
		stockCommandInterpreter.configureCommandInterpreter(model);
		
	}

	/**
	 * Método encargado de generar un comando u otro en función del evento y del argumento que se pasan como parámetros.
	 * @param evento Evento del negocio que se quiere ejecutar.
	 * @param arg Parámetros del evento del negocio que se pasan como comando.
	 * @return Lo que devuelve el comando al ser ejecutado.
	 */
	
	public Object actionStock(int evento, Object arg) {

		stockCommandInterpreter command = stockCommandParser.parseCommand(evento, (String) arg);

		return command.execute();

	}

}