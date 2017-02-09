package com.controller;

import com.controller.command.order.orderCommandInterpreter;
import com.controller.command.order.orderCommandParser;
import com.model.orderService;

/**
 * Controlador del subsistema de pedidos.
 * Es el especialista en creación de comandos en lo referente a pedidos.
 * 
 * @author JAVIER
 */

public class orderController {
	
	private orderService model; //Modelo del subsistema de pedidos
	
	/**
	 * Constructor de un parámetro del controlador del subsistema de pedidos.
	 * @param order Modelo del subsistema de pedidos.
	 */
	
	public orderController(orderService order) {
		model = order;
		orderCommandInterpreter.configureCommandInterpreter(model);
		
	}
	
	/**
	 * Método encargado de generar un comando u otro en función del evento y del argumento que se pasan como parámetros.
	 * @param evento Evento del negocio que se quiere ejecutar.
	 * @param arg Parámetros del evento del negocio que se pasan como comando.
	 * @return Lo que devuelve el comando al ser ejecutado.
	 */
	
	public Object actionOrder(int evento, Object arg) {
		
		orderCommandInterpreter command = orderCommandParser.parseCommand(evento, (String) arg);
		return command.execute();
		
	}
	 
}